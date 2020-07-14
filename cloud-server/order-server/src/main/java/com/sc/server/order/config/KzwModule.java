package com.sc.server.order.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description:    鲲作网模块(设置json解析-类型序列化/反序列化)
 * @author:         **gexiaobing**
 * @create_time:    2019/12/19 1:39 下午
 */
public class KzwModule extends SimpleModule {

    public KzwModule() {
        super(PackageVersion.VERSION);

        // First deserializers
        addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        // then serializers:
        addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
