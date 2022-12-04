package com.zy.design_pattern.interceptor.example1;


public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(TargetInvocation targetInvocation) {
        System.out.println("Logging Begin");
        Response response = targetInvocation.invoke();
        System.out.println("Logging End");
        return response;
    }
}