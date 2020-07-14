package com.sc.server.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author yuantongqin
 * description:
 * 2020/4/29
 * 由于zuul被gateway 取代，zuul源码不分析了，直接看gateway
 */
@RestController
@Slf4j
public class MemberController {

    @GetMapping("/name")
    public String getMember(@RequestParam String name){
        return "member server "+name;
    }


    @GetMapping(value = "/getName/{id}", produces="application/json;charset=UTF-8")
    public String getName(@PathVariable("id") Long Id){
        log.info("member-server 服务处理 id:"+Id);
        int i = ThreadLocalRandom.current().nextInt(100);
        return "根据id="+Id+" 返回的name=张三"+i;
    }
}
