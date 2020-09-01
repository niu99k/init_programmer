package com.gg.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("logger")
@Aspect
public class Logger {

    @Pointcut("execution(* com.gg.service.IService.*(..))")
    public void p1() {
    }

    @Before("p1()")
    public void logBefore() {
        System.out.println("----------------前置通知");
    }

    @AfterReturning("p1()")
    public void logAfter() {
        System.out.println("----------------后置通知");
    }

    @AfterThrowing("p1()")
    public void logException() {
        System.out.println("----------------异常通知");
    }

    @After("p1()")
    public void logFinal() {
        System.out.println("----------------最终通知");
    }

    @Around("p1()")
    public Object logAround(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            Object[] args = pjp.getArgs();//得到方法执行所需的参数

            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。前置");

            rtValue = pjp.proceed(args);//明确调用业务层方法（切入点方法）

            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。后置");

            return rtValue;
        } catch (Throwable t) {
            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。异常");
            throw new RuntimeException(t);
        } finally {
            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。最终");
        }
    }
}
