package hu.elte.pizza.repositories;

import hu.elte.pizza.entities.Income;
import hu.elte.pizza.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Integer> {
    public Iterable<Income> findAllByUser(User user);
}
