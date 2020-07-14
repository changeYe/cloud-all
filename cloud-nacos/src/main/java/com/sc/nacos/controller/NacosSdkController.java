package com.sc.nacos.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author yuantongqin
 * desc:
 * 2020-06-26
 */
public class NacosSdkController {

    public static void main(String[] args) {
        String dataId = "example";
        String groupId = "DEFAULT_GROUP";
        String serverAddr = "localhost:8848";
        try {

            Properties confg = new Properties();

            ConfigService configService = NacosFactory.createConfigService(serverAddr);
            String config = configService.getConfig(dataId, groupId, 2000);
            System.out.println(config);
            configService.addListener(dataId, groupId, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {

                    System.out.println("变动之后的配置："+configInfo);
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
}
