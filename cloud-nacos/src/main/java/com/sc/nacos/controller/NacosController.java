package com.sc.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuantongqin
 * desc:
 * 2020-06-26
 */
@RestController
@RefreshScope
//@NacosPropertySource(dataId = "example",groupId = "DEFAULT_GROUP",autoRefreshed = true)
public class NacosController {


    @NacosValue(value = "${info:hello world}",autoRefreshed = true)
    private String info;


    @Value(value = "${hello: hhh}")
    private String hello;

    @Value(value = "${info:hello world}")
    private String infoa;

    @GetMapping("/get")
    public String hello(String name){
        name = name + hello;
        System.out.println(infoa+"===a");
        return info+" :你好："+name + "=="+infoa;
    }



}
