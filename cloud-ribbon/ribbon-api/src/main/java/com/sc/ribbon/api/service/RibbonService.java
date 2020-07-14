package com.sc.ribbon.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author yuantongqin
 * 2019/11/10
 */
@Service
public class RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    public String hello(String name){
        String forObject = restTemplate.getForObject("http://cloud-server/hello?name=" + name, String.class);
        return forObject;
    }
}
