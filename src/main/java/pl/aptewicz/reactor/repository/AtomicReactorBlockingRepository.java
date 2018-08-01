package pl.aptewicz.reactor.repository;

import org.springframework.data.repository.CrudRepository;
import pl.aptewicz.reactor.model.AtomicReactor;

public interface AtomicReactorBlockingRepository extends CrudRepository<AtomicReactor, String> {

}
