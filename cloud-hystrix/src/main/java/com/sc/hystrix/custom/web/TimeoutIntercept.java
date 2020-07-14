package com.sc.hystrix.custom.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.sc.hystrix.custom.annotation.Timeout;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yuantongqin
 * description:
 * 2020/5/10
 */
@Component
public class TimeoutIntercept implements HandlerInterceptor {

    ExecutorService executorService =  new ThreadPoolExecutor(2, 2,
                                                              0L, TimeUnit.MILLISECONDS,
                                                              new LinkedBlockingQueue<Runnable>());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler != null && handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Timeout timeout = handlerMethod.getMethodAnnotation(Timeout.class);
            if(timeout != null){
                long value = timeout.value();
                TimeUnit unit = timeout.unit();
                String fallbackMethod = timeout.fallbackMethod();
                Method method = handlerMethod.getMethod();
                Future<Object> submit = executorService.submit(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        return method.invoke(handlerMethod.getBean());
                    }
                });
                Object invoke = null;
                try {
                    invoke = submit.get(value,unit);
                }catch (TimeoutException e){
                    // 业务补偿
                    Object bean = handlerMethod.getBean();
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Method declaredMethod = bean.getClass().getDeclaredMethod(fallbackMethod, parameterTypes);
                    invoke = declaredMethod.invoke(bean);
//                Class<?>[] objects = Stream.of(handlerMethod.getMethodParameters()).map(MethodParameter::getParameterType).toArray(Class[]::new);
                }

                response.getWriter().write(String.valueOf(invoke));
                return false;
            }

        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
