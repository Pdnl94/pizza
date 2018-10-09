package hu.elte.pizzaorder.repositories;

import hu.elte.pizzaorder.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
