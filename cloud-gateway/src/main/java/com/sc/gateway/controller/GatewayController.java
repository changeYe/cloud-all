package com.sc.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

/**
 * @author yuantongqin
 * description:
 * 2020/5/17
 */
@RestController
@Slf4j
public class GatewayController {


    @GetMapping("/say")
    public String hello(String name){
        int i = ThreadLocalRandom.current().nextInt(100);


        log.info("你好"+name);
        return name+" 你好" + i;
    }

    public Predicate<ServerWebExchange> aa(){
        return serverWebExchange -> false;
    }

}
