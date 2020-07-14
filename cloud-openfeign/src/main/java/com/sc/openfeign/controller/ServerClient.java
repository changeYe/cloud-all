package com.sc.openfeign.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yuantongqin
 * 2020-05-02
 */
@FeignClient(value = "order-server",fallback = ServerClientHystrix.class)
public interface ServerClient {

    @GetMapping("/name/{age}")
    String getAge(@PathVariable("age") String age);


    @GetMapping("/name/setName")
    String setName(@RequestParam("name")String name);


}
