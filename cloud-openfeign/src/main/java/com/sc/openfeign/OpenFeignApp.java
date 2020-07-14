package com.sc.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yuantongqin
 * 2020-05-02
 */
@SpringBootApplication
@EnableFeignClients
public class OpenFeignApp {


    public static void main(String[] args) {
        SpringApplication.run(OpenFeignApp.class,args);
    }


}
