package pl.aptewicz.reactor.reactor;

import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Random;
import java.util.stream.LongStream;

@SuppressWarnings("Duplicates")
public class PublisherSubscriber {

    private final Random random = new Random();

    @Test
    public void publisherSubscriber() {
        Publisher<Integer> intPublisher = subscriber -> {
            System.out.println(String.format("Subscriber '%s' subscribed!", subscriber));
            subscriber.onNext(random.nextInt());
            subscriber.onComplete();
        };

        Subscriber<Integer> intSubscriber = new Subscriber<Integer>() {
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
        };

        intPublisher.subscribe(intSubscriber);
    }

    @Test
    public void subscription() {
        Publisher<Integer> intPublisher = subscriber -> {
            System.out.println(String.format("Subscriber '%s' subscribed!", subscriber));
            subscriber.onSubscribe(new Subscription() {
                @Override
                public void request(long n) {
                    System.out.println("Subscriber requested " + n + " items");
                    LongStream.range(1, n).forEach(seqItem ->
                            subscriber.onNext(random.nextInt((int) seqItem * 5000)));
                    subscriber.onComplete();
                }

                @Override
                public void cancel() {
                    System.out.println("Subscriber canceled subscription");
                }
            });
        };

        Subscriber<Integer> intSubscriber = new Subscriber<Integer>() {
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
        };

        intPublisher.subscribe(intSubscriber);
    }
}
