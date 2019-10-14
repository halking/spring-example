package com.hal.spring.feignserver.feign.fallback;

import com.hal.spring.feignserver.feign.FeignService;
import org.springframework.stereotype.Component;

/**
 * Created by Lyfen on 2018/10/31.
 */
@Component
public class FeignServiceHystrix implements FeignService {

  @Override
  public String home(String name) {
    return "sorry" + name;
  }
}
