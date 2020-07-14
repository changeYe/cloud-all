package com.sc.server.order.config;/**
 * @author yuantongqin
 * desc:
 * 2020-05-24
 */

import com.sc.server.order.cons.OrderCons;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author yuantongqin
 * desc:
 * 2020-05-24
 */
@Component
@Slf4j
public class LoginIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("user-server 拦截器中");
        return initTraceId(request);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String traceRootId = request.getHeader(OrderCons.TRACE_ROOT_ID);
        if (StringUtils.isNotEmpty(traceRootId)) {
            MDC.remove(traceRootId);
        }
    }

    /**
     * 设置traceID
     * @param request
     */
    private boolean initTraceId(HttpServletRequest request){
        String traceRootId = request.getHeader(OrderCons.TRACE_ROOT_ID);

        if (StringUtils.isEmpty(traceRootId)) {
            traceRootId = UUID.randomUUID().toString().replace("-", "");
        }
        MDC.put(OrderCons.TRACE_ROOT_ID, traceRootId);
        return true;
    }
}
