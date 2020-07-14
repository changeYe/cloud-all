package com.sc.ribbon.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yuantongqin
 * description:
 * 2020/4/25
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserApp {

    public static void main(String[] args) {
        SpringApplication.run(UserApp.class,args);
    }

}
