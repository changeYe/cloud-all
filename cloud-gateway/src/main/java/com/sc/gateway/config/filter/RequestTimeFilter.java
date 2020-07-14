package com.sc.gateway.config.filter;

import com.sc.gateway.cons.GatewayCons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author yuantongqin
 * description:
 * 2020/4/30
 */
public class RequestTimeFilter implements GatewayFilter, Ordered {
    Logger log = LoggerFactory.getLogger(RequestTimeFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put("hello","world");


        String traceRootId = UUID.randomUUID().toString().replace("-","");
        MDC.put(GatewayCons.TRACE_ROOT_ID,traceRootId);
        exchange.getRequest().getHeaders().add(GatewayCons.TRACE_ROOT_ID,traceRootId);


        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute("hello");
                    if (startTime != null) {
                        log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
                    }
                })
        );

    }

    @Override
    public int getOrder() {
        return 0;
    }

}
