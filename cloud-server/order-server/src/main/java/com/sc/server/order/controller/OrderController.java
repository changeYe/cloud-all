package com.sc.server.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sc.server.order.dto.HelloDTO;
import com.sc.server.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author yuantongqin
 * description:
 * 2020/4/29
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/aa")
    public void sayHello(HelloDTO.Param param){

        System.out.println(param.getTime()+"==");
        System.out.println(param.toString());
    }


    @GetMapping(value = "/hello",produces = "application/json;charset=UTF-8")
    public String getOrder(@RequestParam Long id){
        log.info("进去order-server:"+id);
        String order = orderService.getOrder(id);
        return order+" == order server "+ id;
    }


    @GetMapping("/name")
    public String getOrder(@RequestParam String name){
        return "order server "+ name;
    }


    @GetMapping("/name/a")
    public String getName(@RequestParam String name){
        return "order server aaa "+ name;
    }

    @GetMapping("/name/{age}")
    public String getAge(@PathVariable Integer age){

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return age + " 年轻就是好";
    }

    @GetMapping("/name/setName")
    public String setName(@RequestParam("name")String name){
        return "哈哈哈："+name;
    }


    public static void main(String[] args) {
        String a = "";

    }

}
