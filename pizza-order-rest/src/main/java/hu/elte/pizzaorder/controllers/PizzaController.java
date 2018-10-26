package hu.elte.pizzaorder.controllers;

import hu.elte.pizzaorder.repositories.PizzaRepository;
import hu.elte.pizzaorder.entities.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;
    
    @GetMapping("")
    public ResponseEntity<Iterable<Pizza>> getAll() {
        return ResponseEntity.ok(pizzaRepository.findAll());

    }
}
