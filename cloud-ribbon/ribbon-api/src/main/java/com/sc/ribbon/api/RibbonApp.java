package com.sc.ribbon.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yuantongqin
 * 2019/11/10
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RibbonApp {

    public static void main(String[] args) {
        SpringApplication.run(RibbonApp.class,args);
    }

}
