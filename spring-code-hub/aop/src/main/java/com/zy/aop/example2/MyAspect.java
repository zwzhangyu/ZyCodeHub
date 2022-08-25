package com.zy.aop.example2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {


    @Pointcut("execution(* com.zy.aop.example2.UserService.*(..))")
    public void pointcut() {

    }


    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("before ......");
    }


}
