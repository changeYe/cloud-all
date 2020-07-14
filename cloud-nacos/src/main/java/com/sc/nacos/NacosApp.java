package com.sc.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yuantongqin
 * desc:
 * 2020-06-26
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(NacosApp.class, args);
        run.getEnvironment().getPropertySources();

    }
}
