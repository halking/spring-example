package com.hal.labs.web;

import com.hal.labs.common.ResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author: Steven HUANG
 * @Date: 2021/3/26
 */
public class BaseController {

  protected <R> Mono<ResponseDTO<R>> successMono(R data) {
    return Mono.justOrEmpty(ResponseDTO.success(data));
  }

  protected <R> Flux<ResponseDTO<R>> successFlux(R data) {
    return Flux.just(ResponseDTO.success(data));
  }

}
