package com.sc.ribbon.api.controller;

import com.sc.ribbon.api.dto.CommonResult;
import com.sc.ribbon.api.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author yuantongqin
 * 2019/11/10
 */
@RestController
public class RibbonController {

    @Autowired
    private RibbonService ribbonService;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired(required = false)
    private LoadBalancerClient loadBalancer;

    @Value("${service-url.user-service}")
    private String userServiceUrl;

    @GetMapping("/user/{id}")
    public CommonResult getUser(@PathVariable Long id){
        ClientHttpRequestFactory requestFactory = restTemplate.getRequestFactory();
        CommonResult forObject = restTemplate.getForObject(userServiceUrl+"/user/{1}", CommonResult.class, id);

        return forObject;
    }

    @GetMapping("/user/name")
    public CommonResult getUserByName(@RequestParam String name){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(userServiceUrl+"/user/name?name={1}", CommonResult.class, name);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else {
            return new CommonResult();
        }
    }

    @RequestMapping("/hello.do")
    public String hello(String name){
        ServiceInstance serviceInstance = loadBalancer.choose("cloud-server");
        System.out.println("服务地址：" + serviceInstance.getUri());
        System.out.println("服务名称：" + serviceInstance.getServiceId());

        String callServiceResult = restTemplate.getForObject(serviceInstance.getUri().toString() + "/hello?name="+name, String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
    }

    @RequestMapping("/sayHello.do")
    public String say(String name){
        String hello = ribbonService.hello(name);
        return hello;
    }
}
