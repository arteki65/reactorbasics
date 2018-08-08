package pl.aptewicz.reactor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import javax.annotation.PostConstruct;

@Configuration
public class CappedCollectionConfig {

    private final ReactiveMongoTemplate mongoTemplate;

    public CappedCollectionConfig(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void createCappedCollection() {
        mongoTemplate.dropCollection("atomicReactors")
                .then(mongoTemplate.createCollection("atomicReactors", CollectionOptions.empty().capped().size(2048)))
                .subscribe(mongoCollection -> System.out.println("Mongo capped collection created!"));
    }
}
