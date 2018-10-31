package com.hal.spring.ribbonserver.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Lyfen on 2018/10/31.
 */
@Service
public class RibbonTestService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "homeFallback")
    public String RibbonHome(String name){
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }

    public String homeFallback(String name){
        return "hi,"+name+",sorry,server error!";
    }
}
