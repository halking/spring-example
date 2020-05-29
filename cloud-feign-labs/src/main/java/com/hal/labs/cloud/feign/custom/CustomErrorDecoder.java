package com.hal.labs.cloud.feign.custom;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * @Author: Steven HUANG
 * @Date: 2020/5/29
 */
public class CustomErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {

    switch (response.status()){
/*      case 400:
        return new BadRequestException();
      case 404:
        return new NotFoundException();*/
      default:
        return new Exception("Generic error");
    }
  }
}

