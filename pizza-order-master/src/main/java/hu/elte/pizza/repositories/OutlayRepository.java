package hu.elte.pizza.repositories;

import hu.elte.pizza.entities.Outlay;
import hu.elte.pizza.entities.Provider;
import hu.elte.pizza.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutlayRepository extends CrudRepository<Outlay, Integer> {
    public Iterable<Outlay> findAllByUser(User user);    
    public Iterable<Outlay> findAllByProvider(Provider provider);
}