package pl.aptewicz.reactor.reactor;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

@SuppressWarnings("Duplicates")
public class ReactorTypes {

    private final Random random = new Random();

    @Test
    public void publisherSubscriber() {
        Mono.just(random.nextInt()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println(String.format("onSubscribe with '%s' subscription", s));
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Next int " + integer + " received");
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Publisher emits error: " + t);
            }

            @Override
            public void onComplete() {
                System.out.println("Publisher emits complete!");
            }
        });
    }

    @Test
    public void publisherSubscriberLambdas() {
        //@formatter:off
        Mono.just(random.nextInt()).subscribe(
                integer -> System.out.println("Next int " + integer + " received"),
                t -> System.out.println("Publisher emits error: " + t),
                () -> System.out.println("Publisher emits complete!"),
                s -> System.out.println(String.format("onSubscribe with '%s' subscription", s))
        );
        //@formatter:on
    }

    @Test
    public void publisherSubscriberLambdasWorking() {
        //@formatter:off
        Mono.just(random.nextInt()).subscribe(
                integer -> System.out.println("Next int " + integer + " received"),
                t -> System.out.println("Publisher emits error: " + t),
                () -> System.out.println("Publisher emits complete!"),
                s -> {
                    System.out.println(String.format("onSubscribe with '%s' subscription", s));
                    s.request(15L);
                }
        );
        //@formatter:on
    }

    @Test
    public void publisherLog() {
        //@formatter:off
        Mono.just(random.nextInt()).log().subscribe(
                integer -> System.out.println("Next int " + integer + " received")
        );
        //@formatter:on
    }

    @Test
    public void subscription() {
        Flux.range(0, 20).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println(String.format("onSubscribe with '%s' subscription", s));
                s.request(15L);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Next int " + integer + " received");
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Publisher emits error: " + t);
            }

            @Override
            public void onComplete() {
                System.out.println("Publisher emits complete!");
            }
        });
    }

    @Test
    public void subscriptionLambdas() {
        Flux.range(0, 20).log()
                .subscribe(integer -> System.out.println("Next int " + integer + " received"));
    }

    @Test
    public void subscriptionLambdasWithRate() {
        Flux.range(0, 20).log().limitRate(2)
                .subscribe(integer -> System.out.println("Next int " + integer + " received"));
    }
}
