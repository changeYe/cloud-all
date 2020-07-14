package com.sc.openfeign.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuantongqin
 * 2020-05-02
 */
@RestController
@Slf4j
public class OpenFeignController {


    @Autowired
    private ServerClient serverClient;

    @Value("${server.port}")
    private int port;

    @Autowired
    private Environment environment;

    @Autowired
    private ApplicationContext applicationContext;


    public void a(){
//        RootBeanDefinition definition = new RootBeanDefinition(User.class);
//        registry.registerBeanDefinition("userName",definition);
//        User userName = applicationContext.getBean("userName", User.class);
//        System.out.println();

    }

    @GetMapping("/hello/{age}")
    public String getAge(@PathVariable String age){
        String port = environment.getProperty("local.server.port");
        log.info(port  + "开始了："+ age);
        a();

        return serverClient.getAge(age);
    }

    @GetMapping("/hello")
    public String hello(String name){
        serverClient.setName(name);
        return "good morning "+ name;
    }
}
