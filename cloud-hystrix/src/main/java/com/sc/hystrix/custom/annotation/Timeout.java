package com.sc.hystrix.custom.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author yuantongqin
 * description:
 * 2020/5/10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Timeout {

    /**
     * 超时时间
     * @return
     */
    long value();

    /**
     * 异常处理方法
     */
    String fallbackMethod();

    /**
     * 时间单位
     * @return
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;

}
