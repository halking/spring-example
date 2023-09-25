package com.hal.example.reactor;/*
package com.hal.sample.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@RunWith(JUnit4.class)
public class FluxTest {

    @Test
    public void testCreate() {
        Flux.create(sink -> {
            sink.next("1");
        }).subscribe(System.out::println);
    }

    @Test
    public void emptyTest() {
        log.info("Before");
        Flux.empty().subscribe(System.out::println, System.out::println, () -> log.info("complete"));
        log.info("After");
    }

    @Test
    public void testSubscriber() {
        Mono<String> m1 = Mono.just("1");
        m1.subscribe(log::info);

        Mono<String> m2 = Mono.create(stringMonoSink -> stringMonoSink.success("sink"));
        m2.subscribe(log::info);
    }
}
*/
