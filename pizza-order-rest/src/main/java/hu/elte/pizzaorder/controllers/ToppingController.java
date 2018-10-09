package hu.elte.pizzaorder.controllers;

import hu.elte.pizzaorder.repositories.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/toppings")
public class ToppingController {
    @Autowired
    private ToppingRepository toppingRepository;
}
