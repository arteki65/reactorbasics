package pl.aptewicz.reactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    /*@Bean
    public CommandLineRunner run(AtomicReactorReactiveRepository repository) {
        return (args) -> repository
                .save(AtomicReactor.builder().name("test reactor").probabilityOfExplosion(new BigDecimal("0.4"))
                        .build()).log().doOnNext(saved -> System.out.println(saved + " saved to mongodb")).flux().log()
                .flatMap(atomicReactor -> repository.findAll())
                .subscribe(atomicReactor -> System.out.println("Atomic reactor from mongodb: " + atomicReactor));
    }*/
}
