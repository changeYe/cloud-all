package com.sc.server.order.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @description:    鲲作网自定义entityMapper
 * @author:         **gexiaobing**
 * @create_time:    2019/12/19 1:27 下午
 */
public class KzwEntityMapper {

    private ObjectMapper objectMapper;

    public KzwEntityMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.registerModule(new KzwModule());
    }

}
