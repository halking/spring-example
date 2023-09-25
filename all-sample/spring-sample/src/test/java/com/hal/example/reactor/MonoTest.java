package com.hal.example.reactor;/*
package com.hal.sample.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@Slf4j
@RunWith(JUnit4.class)
public class MonoTest {

  @Test
  public void testCreate() {
    Mono<String> m1 = Mono.just("1");

    StepVerifier.create(m1).expectNext("1").verifyComplete();

    Mono<String> m2 = Mono.create(stringMonoSink -> stringMonoSink.success("sink"));
    StepVerifier.create(m2).expectNext("sink").verifyComplete();
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
