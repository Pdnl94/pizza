package hu.elte.pizza.repositories;

import hu.elte.pizza.entities.ItemCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends CrudRepository<ItemCategory, Integer> {

}
