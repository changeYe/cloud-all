package com.sc.hystrix.service.impl;

import java.util.concurrent.ThreadLocalRandom;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.sc.hystrix.service.HystrixService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author yuantongqin
 * description: Hystrix 配置属性
 * 2020/5/9
 * https://github.com/Netflix/Hystrix/wiki/Configuration
 */
@Service
public class HystrixServiceImpl implements HystrixService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${server-url.order-service}")
    private String orderServiceUrl;


    @Override
    @HystrixCommand( fallbackMethod = "getDefaultName", commandKey = "getName",
            commandProperties={
            @HystrixProperty(name = "execution.isolation.strategy",value = "THxREAD"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "50"),
    })
    public String getName(String name) {
        await();
        return "范无端处理接口："+name;
    }

    @Override
    @HystrixCommand(fallbackMethod = "getDefaultName", commandKey = "getName")
    public String getHystrixName(String name) {
        String forObject = restTemplate.getForObject(orderServiceUrl + "/name/{age}", String.class, name);
        System.out.println("结果："+forObject);
        return "哈哈哈："+name;
    }

    private void await(){
        int i = ThreadLocalRandom.current().nextInt(100);
        try {
            System.out.println(Thread.currentThread().getName()+"睡眠："+i);
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    @CacheRemove(commandKey = "getName")
    public String removeCache(String name){
        if(StringUtils.isEmpty(name)){
            
        }
        
        if(StringUtils.isNotEmpty(name)){
            
        }
        return "remove "+name;
    }


    /**
     * 同于key的生成方法
     * @CacheResult 会调用这个方法
     * @param name
     * @return
     */
    public String getCacheNameKey(String name){
        int i = ThreadLocalRandom.current().nextInt(100);
        return i+"==key=="+name;
    }

    /**
     * 这是服务端服务降级
     * @param name
     * @return
     */
    public String getDefaultName(String name){
        return "服务降级："+name;
    }


}
