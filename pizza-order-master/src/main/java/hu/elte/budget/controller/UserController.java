package hu.elte.budget.controller;

import hu.elte.budget.entities.User;
import hu.elte.budget.repositories.UserRepository;
import hu.elte.budget.security.AuthenticatedUser;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @GetMapping("")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Iterable<User>> getAll() {
        User user = authenticatedUser.getUser();
        if (user != null) {
            User.Role role = user.getRole();
            if (role.equals(User.Role.ROLE_ADMIN)) {
                return ResponseEntity.ok(userRepository.findAll());
            } else {
                return ResponseEntity.status(403).build();
            }
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUsername(user.getUsername());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRole(User.Role.ROLE_USER);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("login")
    public ResponseEntity<User> login() {
        return ResponseEntity.ok(authenticatedUser.getUser());
    }
    
    @GetMapping("balance")
    public ResponseEntity<Integer> getBalance() {
        return ResponseEntity.ok(authenticatedUser.getUser().getBalance());
    }
}
