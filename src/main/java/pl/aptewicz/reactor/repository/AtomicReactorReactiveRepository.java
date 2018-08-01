package pl.aptewicz.reactor.repository;

import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pl.aptewicz.reactor.model.AtomicReactor;
import reactor.core.publisher.Flux;

public interface AtomicReactorReactiveRepository extends ReactiveCrudRepository<AtomicReactor, String> {

    @Tailable
    Flux<AtomicReactor> findWithTailableCursorBy();
}
