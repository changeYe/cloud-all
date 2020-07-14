package com.sc.hystrix.custom.web;

import java.lang.reflect.Method;
import java.util.concurrent.Semaphore;

import com.netflix.hystrix.contrib.javanica.utils.AopUtils;
import com.sc.hystrix.custom.annotation.Limited;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author yuantongqin
 * description:
 * 2020/5/10
 */
@Aspect
public class AopConfig {

    private Semaphore semaphore;

    @Pointcut("@annotation(com.sc.hystrix.custom.annotation.Limited)")
    public void LimitedAnnotationPointcut() {
    }

    @Around("LimitedAnnotationPointcut()")
    public Object methodsAnnotatedWithLimited(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object value = null;
        if(joinPoint.getSignature() instanceof MethodSignature){
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            methodSignature.getMethod();
            Method method = AopUtils.getMethodFromTarget(joinPoint);
            Limited limited = method.getAnnotation(Limited.class);
            int permit = limited.value();
            Semaphore semaphore = initSemaphore(permit);
            try {
                semaphore.acquire();
                value = joinPoint.proceed();
            }finally {
                semaphore.release();
            }
        }
        return value;
    }

    public Semaphore initSemaphore(int permit){
        if(semaphore == null){
            semaphore = new Semaphore(permit);
        }
        return semaphore;
    }

}
