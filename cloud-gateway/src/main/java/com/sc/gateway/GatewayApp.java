package com.sc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * @author yuantongqin
 * description:
 * 2020/4/29
 */
@SpringBootApplication
@RestController
public class GatewayApp {

    

    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class,args);
    }


    /**
     * 使用RouteLocator的Bean进行路由转发，将请求进行处理，最后转发到目标的下游服务。
     * 创建RouteLocator路由规定的目的，对请求做断言predicates(根据具体的请求的规则，由具体的route去处理)
     * 与过滤处理filter ，用来对请求做各种判断和修改。
     *
     * 这里创建的route可以让请求“/name”请求都转发到“http://localhost:8082/name”。
     * 在route配置上，我们添加了一个filter，该filter会将请求添加一个header,key为hello，value为world。
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){

        return routeLocatorBuilder.routes()
                .route("path_route", predicateSpec -> {
                    // 拦截name开头的所有请求
                    return predicateSpec.path("/name")
                            // 拦截之后做过滤处理
                            .filters(gatewayFilterSpec -> {
                                gatewayFilterSpec.addRequestHeader("hello", "world");

                                return gatewayFilterSpec;
                            })
                            // 过滤处理之后将请求转换到另一个url处理
                            .uri("http://localhost:8083/hello?id=1");
                })
//                .route("path_route1",r->r.path("/order-server/**").uri("http://localhost:8083/"))
                .route("path_route2", new Function<PredicateSpec, Route.AsyncBuilder>() {
                    @Override
                    public Route.AsyncBuilder apply(PredicateSpec r) {
                        Route.AsyncBuilder uri = r.path("/hello")
//                                .filters(new Function<GatewayFilterSpec, UriSpec>() {
//                                    @Override
//                                    public UriSpec apply(GatewayFilterSpec gatewayFilterSpec) {
//                                        gatewayFilterSpec.filter(new RequestTimeFilter());
//                                        return gatewayFilterSpec;
//                                    }
//                                })
                                .uri("http://localhost:8083/hello");
                        return uri;
                    }
                })
                .build();

    }

    public void aa(){
        BigDecimal bigDecimal = BigDecimal.valueOf(10);

    }

}
