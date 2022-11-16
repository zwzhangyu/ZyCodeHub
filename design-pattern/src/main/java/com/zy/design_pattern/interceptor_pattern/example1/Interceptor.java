package com.zy.design_pattern.interceptor_pattern.example1;


public interface Interceptor {
    public Response intercept(TargetInvocation targetInvocation);
}