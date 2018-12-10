package hu.elte.budget.repositories;

import hu.elte.budget.entities.ItemCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends CrudRepository<ItemCategory, Integer> {

}
