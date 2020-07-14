package com.sc.zuul.config;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yuantongqin
 * description:
 * 2020/4/29
 */
@Component
@Slf4j
public class CustomFilter extends ZuulFilter {

    /**
     * "pre" 路由之前
     * "route" 路由之时
     * "post" 路由之后
     * "error" 发送错误调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的顺序
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * 这里可以写逻辑判断，是否要过滤
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     */
    @Override
    public Object run() throws ZuulException {
        // requestContext 保存了请求的所有数据，包括 request,response
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String queryString = request.getQueryString();
        log.info("做一个日志记录：" +queryString);
        return null;
    }
}
