package hu.elte.pizzaorder.controllers;

import hu.elte.pizzaorder.entities.Order;
import hu.elte.pizzaorder.repositories.OrderRepository;
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
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private AuthenticatedUser authenticatedUser;
    
    @GetMapping("")
    public ResponseEntity<Iterable<Order>> getAll() {
        return ResponseEntity.ok(orderRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("")
    public ResponseEntity<Order> post(@RequestBody Order order) {
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Integer id, @RequestBody Order order) {
        User user = authenticatedUser.getUser();
        User.Role role = user.getRole();
        Optional<Order> oOrder = orderRepository.findById(id);
        if (oOrder.isPresent() && role.equals(User.Role.ADMIN)) {
            order.setId(id);
            return ResponseEntity.ok(orderRepository.save(order));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(@PathVariable Integer id) {
        User user = authenticatedUser.getUser();
        User.Role role = user.getRole();
        Optional<Order> oOrder = orderRepository.findById(id);
        if (oOrder.isPresent() && role.equals(User.Role.ADMIN)) {
            orderRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
