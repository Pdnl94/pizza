package hu.elte.budget.controller;

import hu.elte.budget.entities.Item;
import hu.elte.budget.entities.Outlay;
import hu.elte.budget.entities.User;
import hu.elte.budget.repositories.ItemRepository;
import hu.elte.budget.repositories.OutlayRepository;
import hu.elte.budget.repositories.ProviderRepository;
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
@RequestMapping("/outlays")
public class OutlayController {

    @Autowired
    private OutlayRepository outlayRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Outlay>> getAll() {
        User user = authenticatedUser.getUser();
        User.Role role = user.getRole();
        if (role.equals(User.Role.ROLE_ADMIN)) {
            return ResponseEntity.ok(outlayRepository.findAll());
        } else {
            return ResponseEntity.ok(outlayRepository.findAllByUser(user));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Outlay> get(@PathVariable Integer id) {
        User user = authenticatedUser.getUser();
        Optional<Outlay> outlay = outlayRepository.findById(id);
        if (outlay.isPresent()) {
            if (outlay.get().getUser().getId() == user.getId() || user.getRole() == User.Role.ROLE_ADMIN) {
                return ResponseEntity.ok(outlay.get());
            } else {
                return ResponseEntity.status(403).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Outlay> post(@RequestBody Outlay outlay) {
        User user = authenticatedUser.getUser();
        outlay.setUser(user);
        Outlay savedOutlay = outlayRepository.save(outlay);
        user.setBalance(user.getBalance() - outlay.getPrice());
        userRepository.save(user);
        return ResponseEntity.ok(savedOutlay);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Outlay> update(@PathVariable Integer id, @RequestBody Outlay outlay) {
        User user = authenticatedUser.getUser();
        Optional<Outlay> oOutlay = outlayRepository.findById(id);
        if (oOutlay.isPresent()) {
            if (oOutlay.get().getUser().getId() == user.getId() || user.getRole() == User.Role.ROLE_ADMIN) {
                user.setBalance(user.getBalance() + oOutlay.get().getPrice() - outlay.getPrice());
                outlay.setId(id);
                outlay.setUser(oOutlay.get().getUser());
                Outlay savedOutlay = outlayRepository.save(outlay);
                userRepository.save(user);
                return ResponseEntity.ok(savedOutlay);
            } else {
                return ResponseEntity.status(403).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Outlay> delete(@PathVariable Integer id) {
        User user = authenticatedUser.getUser();
        Optional<Outlay> oOutlay = outlayRepository.findById(id);
        if (oOutlay.isPresent()) {
            Outlay outlay = oOutlay.get();
            if (outlay.getUser().getId() == user.getId() || user.getRole() == User.Role.ROLE_ADMIN) {
                user.setBalance(user.getBalance() + outlay.getPrice());
                outlayRepository.deleteById(id);
                userRepository.save(user);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(403).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<Iterable<Item>> getItems(@PathVariable Integer id) {
        User user = authenticatedUser.getUser();
        Optional<Outlay> oOutlay = outlayRepository.findById(id);
        if (oOutlay.isPresent()) {
            Outlay outlay = oOutlay.get();
            if (outlay.getUser().getId() == user.getId() || user.getRole() == User.Role.ROLE_ADMIN) {
                return ResponseEntity.ok(outlay.getItems());
            } else {
                return ResponseEntity.status(403).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<Outlay> addItem(@PathVariable Integer id, @RequestBody Item item) {
        User user = authenticatedUser.getUser();
        Optional<Outlay> oOutlay = outlayRepository.findById(id);
        if (oOutlay.isPresent()) {
            Outlay outlay = oOutlay.get();
            if (outlay.getUser() == user || user.getRole() == User.Role.ROLE_ADMIN) {
                item.setOutlay(outlay);
                Item newItem = itemRepository.save(item);
                outlay.getItems().add(newItem);
                return ResponseEntity.ok(outlayRepository.save(outlay));
            } else {
                return ResponseEntity.status(403).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/items/{itemid}")
    public ResponseEntity<Outlay> deleteItem(@PathVariable Integer id, @PathVariable Integer itemid) {
        User user = authenticatedUser.getUser();
        Optional<Outlay> oOutlay = outlayRepository.findById(id);
        if (oOutlay.isPresent()) {
            Outlay outlay = oOutlay.get();
            if (outlay.getUser() == user || user.getRole() == User.Role.ROLE_ADMIN) {
                Optional<Item> oItem = itemRepository.findById(itemid);
                if (oItem.isPresent()) {
                    Item item = oItem.get();
                    outlay.getItems().remove(item);
                    itemRepository.delete(item);
                    return ResponseEntity.ok(outlayRepository.save(outlay));
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.status(403).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Iterable<Outlay>> getAllByUser(@PathVariable Integer id
    ) {
        User user = authenticatedUser.getUser();
        User.Role role = user.getRole();
        if (role.equals(User.Role.ROLE_ADMIN)) {
            Optional<User> searchedUser = userRepository.findById(id);
            if (searchedUser.isPresent()) {
                return ResponseEntity.ok(outlayRepository.findAllByUser(searchedUser.get()));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(403).build();
        }
    }
}
