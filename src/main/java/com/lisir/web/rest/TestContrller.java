package com.lisir.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author lxp
 * @Date 2021-02-07 11:37
 * @Version 1.0
 */
@RestController
public class TestContrller {

    @GetMapping("/test")
    public String test(){

        return "hello, docker";
    }

}
