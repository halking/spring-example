package com.hal.spring.feignserver.feign;

import com.hal.spring.feignserver.feign.fallback.FeignServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Lyfen on 2018/10/31.
 */
@FeignClient(value = "service-hi", fallback = FeignServiceHystrix.class)
public interface FeignService {

  @RequestMapping(value = "/hi", method = RequestMethod.GET)
  String home(@RequestParam(value = "name") String name);
}
