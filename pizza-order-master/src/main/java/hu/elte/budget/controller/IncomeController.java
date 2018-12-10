package hu.elte.budget.controller;

import hu.elte.budget.entities.Income;
import hu.elte.budget.entities.User;
import hu.elte.budget.repositories.IncomeRepository;
import hu.elte.budget.repositories.UserRepository;
import hu.elte.budget.security.AuthenticatedUser;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/incomes")
public class IncomeController {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Income>> getAll() {
        User user = authenticatedUser.getUser();
        if (user == null) {
            return ResponseEntity.status(403).build();
        }
        User.Role role = user.getRole();
        if (role.equals(User.Role.ROLE_ADMIN)) {
            return ResponseEntity.ok(incomeRepository.findAll());
        } else {
            return ResponseEntity.ok(incomeRepository.findAllByUser(user));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> get(@PathVariable Integer id) {
        User user = authenticatedUser.getUser();
        if (user == null) {
            return ResponseEntity.status(403).build();
        }
        Optional<Income> income = incomeRepository.findById(id);
        if (income.isPresent()) {
            if (income.get().getUser().getId() == user.getId() || user.getRole() == User.Role.ROLE_ADMIN) {
            return ResponseEntity.ok(income.get());
            } else {
                return ResponseEntity.status(403).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Income> post(@RequestBody Income income) {
        User user = authenticatedUser.getUser();
        income.setUser(user);
        Income savedIncome = incomeRepository.save(income);
        user.setBalance(user.getBalance() + income.getAmount());
        userRepository.save(user);
        return ResponseEntity.ok(savedIncome);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Income> update(@PathVariable Integer id, @RequestBody Income income) {
        User user = authenticatedUser.getUser();
        Optional<Income> oIncome = incomeRepository.findById(id);
        if (oIncome.isPresent()) {
            if (oIncome.get().getUser().getId() == user.getId() || user.getRole() == User.Role.ROLE_ADMIN) {
                user.setBalance(user.getBalance() - oIncome.get().getAmount() + income.getAmount());
                income.setId(id);
                income.setUser(oIncome.get().getUser());
                Income savedIncome = incomeRepository.save(income);
                userRepository.save(user);
                return ResponseEntity.ok(savedIncome);
            } else {
                return ResponseEntity.status(403).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Income> delete(@PathVariable Integer id) {
        User user = authenticatedUser.getUser();
        Optional<Income> oIncome = incomeRepository.findById(id);
        if (oIncome.isPresent()) {
            if (oIncome.get().getUser().getId() == user.getId() || user.getRole() == User.Role.ROLE_ADMIN) {
                user.setBalance(user.getBalance() - oIncome.get().getAmount());
                incomeRepository.deleteById(id);
                userRepository.save(user);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(403).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Iterable<Income>> getAllByUser(@PathVariable Integer id) {
        User user = authenticatedUser.getUser();
        User.Role role = user.getRole();
        if (role.equals(User.Role.ROLE_ADMIN)) {
            Optional<User> searchedUser = userRepository.findById(id);
            if (searchedUser.isPresent()) {
                return ResponseEntity.ok(incomeRepository.findAllByUser(searchedUser.get()));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(403).build();
        }
    }
}
