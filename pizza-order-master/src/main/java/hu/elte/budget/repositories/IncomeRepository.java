package hu.elte.budget.repositories;

import hu.elte.budget.entities.Income;
import hu.elte.budget.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Integer> {
    public Iterable<Income> findAllByUser(User user);
}
