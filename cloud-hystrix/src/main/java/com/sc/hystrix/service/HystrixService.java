package com.sc.hystrix.service;

/**
 * @author yuantongqin
 * description:
 * 2020/5/9
 */
public interface HystrixService {

    String getName(String name);

    String getHystrixName(String name);

    String removeCache(String name);


}
