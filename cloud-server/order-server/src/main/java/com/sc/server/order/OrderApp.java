package com.sc.server.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yuantongqin
 * description:
 * 2020/4/29
 */
@SpringBootApplication(scanBasePackages = {"com.sc"})
@EnableFeignClients

public class OrderApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class,args);
    }

}
