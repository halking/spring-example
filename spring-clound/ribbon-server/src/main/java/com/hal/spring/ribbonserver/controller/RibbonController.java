package com.hal.spring.ribbonserver.controller;

import com.hal.spring.ribbonserver.service.RibbonTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lyfen on 2018/10/31.
 */
@RestController
public class RibbonController {

    @Autowired
    RibbonTestService ribbonTestService;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return ribbonTestService.RibbonHome(name);
    }
}
