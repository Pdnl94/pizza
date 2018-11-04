package hu.elte.pizzaorder.controllers;

import hu.elte.pizzaorder.entities.Topping;
import hu.elte.pizzaorder.entities.User;
import hu.elte.pizzaorder.repositories.ToppingRepository;
import hu.elte.pizzaorder.security.AuthenticatedUser;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/toppings")
public class ToppingController {
    @Autowired
    private ToppingRepository toppingRepository;
    
    @Autowired
    private AuthenticatedUser authenticatedUser;
    
    @GetMapping("")
    public ResponseEntity<Iterable<Topping>> getAll() {
        return ResponseEntity.ok(toppingRepository.findAll());
    }
        
    @GetMapping("/{id}")
    public ResponseEntity<Topping> get(@PathVariable Integer id) {
        Optional<Topping> topping = toppingRepository.findById(id);
        if (topping.isPresent()) {
            return ResponseEntity.ok(topping.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("")
    public ResponseEntity<Topping> post(@RequestBody Topping topping) {
        User user = authenticatedUser.getUser();
        User.Role role = user.getRole();
        if (role.equals(User.Role.ADMIN)) {
            Topping savedTopping = toppingRepository.save(topping);
            return ResponseEntity.ok(savedTopping);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Topping> update(@PathVariable Integer id, @RequestBody Topping topping) {
        User user = authenticatedUser.getUser();
        User.Role role = user.getRole();
        Optional<Topping> oTopping = toppingRepository.findById(id);
        if (oTopping.isPresent() && role.equals(User.Role.ADMIN)) {
            topping.setId(id);
            return ResponseEntity.ok(toppingRepository.save(topping));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Topping> delete(@PathVariable Integer id) {
        User user = authenticatedUser.getUser();
        User.Role role = user.getRole();
        Optional<Topping> oTopping = toppingRepository.findById(id);
        if (oTopping.isPresent() && role.equals(User.Role.ADMIN)) {
            toppingRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
