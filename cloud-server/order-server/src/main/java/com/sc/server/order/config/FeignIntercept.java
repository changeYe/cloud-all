package com.sc.server.order.config;

import com.sc.server.order.cons.OrderCons;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * @author yuantongqin
 * desc:
 * 2020-05-24
 */
@Component
@Slf4j
public class FeignIntercept implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String value = MDC.get(OrderCons.TRACE_ROOT_ID);
        log.info("日志traceId传递："+value);
        requestTemplate.header(OrderCons.TRACE_ROOT_ID,value);
    }
}
