package com.sc.hystrix.controller;

import com.sc.hystrix.custom.annotation.Limited;
import com.sc.hystrix.service.HystrixService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuantongqin
 * description:
 * 2020/5/9
 */
@RestController
public class HystrixController {

    Logger logger = LoggerFactory.getLogger(HystrixController.class);
    @Autowired
    private HystrixService hystrixService;

    @GetMapping("/name")
    public String getName(String name){
        logger.info("调用外部服务，hystrix 也适合用于客户端熔断");
        return hystrixService.getName(name);
    }

    @GetMapping("/name/hystrix")
    public String getHystrixName(String name){
        logger.info("调用外部服务，hystrix 也适合用于客户端熔断");
        return hystrixService.getHystrixName(name);
    }

    @GetMapping("/remove/name")
    @Limited(value = 10)
    public String getRemoveName(String name){
        logger.info("调用外部服务，hystrix 也适合用于客户端熔断");
        return hystrixService.removeCache(name);
    }


    /**
     * 自定义熔断
     */




}
