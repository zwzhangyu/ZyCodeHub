package com.zy.design_pattern.interceptor.example1;


public interface Interceptor {
    public Response intercept(TargetInvocation targetInvocation);
}