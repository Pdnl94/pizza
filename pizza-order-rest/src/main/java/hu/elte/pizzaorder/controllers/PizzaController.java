package hu.elte.pizzaorder.controllers;

import hu.elte.pizzaorder.repositories.PizzaRepository;
import hu.elte.pizzaorder.entities.Pizza;
import hu.elte.pizzaorder.repositories.ToppingRepository;
import hu.elte.pizzaorder.entities.Topping;
import hu.elte.pizzaorder.entities.User;
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
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;
    
    @Autowired
    private ToppingRepository toppingRepository;
    
    @Autowired
    private AuthenticatedUser authenticatedUser;
    
    @GetMapping("")
    public ResponseEntity<Iterable<Pizza>> getAll() {
        return ResponseEntity.ok(pizzaRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> get(@PathVariable Integer id) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if (pizza.isPresent()) {
            return ResponseEntity.ok(pizza.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("")
    public ResponseEntity<Pizza> post(@RequestBody Pizza pizza) {
        User user = authenticatedUser.getUser();
        User.Role role = user.getRole();
        if (role.equals(User.Role.ADMIN)) {
            Pizza savedPizza = pizzaRepository.save(pizza);
            return ResponseEntity.ok(savedPizza);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Integer id, @RequestBody Pizza pizza) {
        User user = authenticatedUser.getUser();
        User.Role role = user.getRole();
        Optional<Pizza> oPizza = pizzaRepository.findById(id);
        if (oPizza.isPresent() && role.equals(User.Role.ADMIN)) {
            pizza.setId(id);
            return ResponseEntity.ok(pizzaRepository.save(pizza));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Integer id) {
        User user = authenticatedUser.getUser();
        User.Role role = user.getRole();
        Optional<Pizza> oPizza = pizzaRepository.findById(id);
        if (oPizza.isPresent() && role.equals(User.Role.ADMIN)) {
            pizzaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /*@PostMapping("/{id}/toppings")
    public ResponseEntity<Topping> insertTopping(@PathVariable Integer id, @RequestBody Topping topping) {
        Optional<Pizza> oPizza = pizzaRepository.findById(id);
        if (oPizza.isPresent()) {
            Pizza pizza = oPizza.get();
            Topping newTopping = toppingRepository.save(topping);
            pizza.getToppings().add(newTopping);
            pizzaRepository.save(pizza);
            return ResponseEntity.ok(newTopping);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
}
