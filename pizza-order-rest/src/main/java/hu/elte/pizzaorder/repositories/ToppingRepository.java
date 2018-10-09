package hu.elte.pizzaorder.repositories;

import hu.elte.pizzaorder.entities.Topping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends CrudRepository<Topping, Integer> {

}
