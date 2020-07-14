package com.sc.ribbon.api.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author yuantongqin
 * 2019/11/10
 */
@Configuration
public class RibbonConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15 * 1000);
        factory.setReadTimeout(15 * 1000);
        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate;
    }

    @Qualifier
    @Bean
    public RestTemplate restTemplate1(){return null;}

    public void ss(@Qualifier("restTemplate1") RestTemplate restTemplate1){

    }



    public void ribbonClientConfig() {
//        DefaultClientConfigImpl.set(CommonClientConfigKey.ConnectTimeout, 1000);
//        DefaultClientConfigImpl.set(CommonClientConfigKey.ReadTimeout, 1000);
    }

//    @Bean
//    public LoadBalancerClient loadBalancerClient(){
//        return null;
//    }

}
