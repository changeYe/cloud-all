package com.sc.openfeign.controller;

import org.springframework.stereotype.Component;

/**
 * @author yuantongqin
 * 2020-05-06
 */
@Component
public class ServerClientHystrix implements ServerClient {
    @Override
    public String getAge(String age) {
        return "服务降级 age:"+age;
    }

    @Override
    public String setName(String name) {
        return "服务降级 name:"+name;
    }
}
