package hu.elte.pizza.controller;

import hu.elte.pizza.entities.ItemCategory;
import hu.elte.pizza.entities.Provider;
import hu.elte.pizza.repositories.ItemCategoryRepository;
import hu.elte.pizza.repositories.ProviderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ItemCategoryRepository categoryRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Provider>> getAll() {
        return ResponseEntity.ok(providerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Provider> get(@PathVariable Integer id) {
        Optional<Provider> provider = providerRepository.findById(id);
        if (provider.isPresent()) {
            return ResponseEntity.ok(provider.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Provider> post(@RequestBody Provider provider) {
        provider.setCategories(new ArrayList<ItemCategory>());
        
        Provider savedProvider = providerRepository.save(provider);
        return ResponseEntity.ok(savedProvider);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Provider> update(@PathVariable Integer id, @RequestBody Provider provider) {
        Optional<Provider> oProvider = providerRepository.findById(id);
        if (oProvider.isPresent()) {
            provider.setId(id);
            provider.setCategories(oProvider.get().getCategories());
            return ResponseEntity.ok(providerRepository.save(provider));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Provider> delete(@PathVariable Integer id) {
        Optional<Provider> oProvider = providerRepository.findById(id);
        if (oProvider.isPresent()) {
            providerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<Iterable<ItemCategory>> getCategory(@PathVariable Integer id) {
        Optional<Provider> provider = providerRepository.findById(id);
        if (provider.isPresent()) {
            return ResponseEntity.ok(provider.get().getCategories());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/categories")
    public ResponseEntity<Provider> addCategory(@PathVariable Integer id, @RequestBody ItemCategory cat) {
        Optional<Provider> oProvider = providerRepository.findById(id);
        if (oProvider.isPresent()) {
            Provider provider = oProvider.get();
            ItemCategory providerCategory = null;
            for (ItemCategory category : categoryRepository.findAll()) {
                if (category.getName().equals(cat.getName())) {
                    providerCategory = category;
                    break;
                }
            }
            List<ItemCategory> categories = provider.getCategories();
            if (providerCategory != null) {
                provider.getCategories().add(providerCategory);
            } else {
                providerCategory = new ItemCategory();
                providerCategory.setName(cat.getName());
                ItemCategory newCategory = categoryRepository.save(providerCategory);
                provider.getCategories().add(newCategory);
            }
            providerRepository.save(provider);
            return ResponseEntity.ok(provider);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}/categories/{catid}")
    public ResponseEntity<Iterable<ItemCategory>> deleteCategory(@PathVariable Integer id, @PathVariable Integer catid) {
        Optional<Provider> provider = providerRepository.findById(id);
        if (provider.isPresent()) {
            ArrayList<ItemCategory> categories = new ArrayList<>();
            for (ItemCategory category : provider.get().getCategories()) {
                if (category.getId() != catid) {
                    categories.add(category);
                }
            }
            provider.get().setCategories(categories);
            return ResponseEntity.ok(provider.get().getCategories());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
