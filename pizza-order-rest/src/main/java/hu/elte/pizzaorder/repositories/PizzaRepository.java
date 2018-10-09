package hu.elte.pizzaorder.repositories;

import hu.elte.pizzaorder.entities.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Integer> {

}
