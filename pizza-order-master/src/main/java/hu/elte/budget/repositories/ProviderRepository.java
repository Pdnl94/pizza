package hu.elte.budget.repositories;

import hu.elte.budget.entities.Provider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Integer> {
    
}