package hu.elte.budget.repositories;

import hu.elte.budget.entities.Item;
import hu.elte.budget.entities.Outlay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
    public Iterable<Item> findAllByOutlay(Outlay outlay);    
}
