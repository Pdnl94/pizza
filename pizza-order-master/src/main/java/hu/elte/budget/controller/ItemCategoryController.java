package hu.elte.budget.controller;

import hu.elte.budget.entities.ItemCategory;
import hu.elte.budget.repositories.ItemCategoryRepository;
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
@RequestMapping("/categories")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryRepository categoryRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<ItemCategory>> getAll() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCategory> get(@PathVariable Integer id) {
        Optional<ItemCategory> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<ItemCategory> post(@RequestBody ItemCategory category) {
        ItemCategory savedItemCategory = categoryRepository.save(category);
        return ResponseEntity.ok(savedItemCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCategory> update(@PathVariable Integer id, @RequestBody ItemCategory category) {
        Optional<ItemCategory> oItemCategory = categoryRepository.findById(id);
        if (oItemCategory.isPresent()) {
            category.setId(id);
            return ResponseEntity.ok(categoryRepository.save(category));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemCategory> delete(@PathVariable Integer id) {
        Optional<ItemCategory> oItemCategory = categoryRepository.findById(id);
        if (oItemCategory.isPresent()) {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
