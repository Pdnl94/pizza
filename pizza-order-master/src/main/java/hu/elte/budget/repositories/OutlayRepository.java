package hu.elte.budget.repositories;

import hu.elte.budget.entities.Outlay;
import hu.elte.budget.entities.Provider;
import hu.elte.budget.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutlayRepository extends CrudRepository<Outlay, Integer> {
    public Iterable<Outlay> findAllByUser(User user);    
    public Iterable<Outlay> findAllByProvider(Provider provider);
}