package hu.elte.pizza.repositories;

import hu.elte.pizza.entities.Item;
import hu.elte.pizza.entities.Outlay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
    public Iterable<Item> findAllByOutlay(Outlay outlay);    
}
