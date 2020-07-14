package com.sc.server.member.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import com.sc.server.member.cons.MemberCons;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

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
        log.info("member-server 拦截器中");
        return initTraceId(request);
    }

    /**
     * 设置traceID
     */
    private boolean initTraceId(HttpServletRequest request) {
        String traceRootId = request.getHeader(MemberCons.TRACE_ROOT_ID);
        if (StringUtils.isEmpty(traceRootId)) {
            traceRootId = UUID.randomUUID().toString().replace("-", "");
        }
        MDC.put(MemberCons.TRACE_ROOT_ID, traceRootId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String traceRootId = request.getHeader(MemberCons.TRACE_ROOT_ID);
        if (StringUtils.isNotEmpty(traceRootId)) {
            MDC.remove(traceRootId);
        }

    }
}
