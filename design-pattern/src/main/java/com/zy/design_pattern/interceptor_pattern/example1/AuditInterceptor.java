package com.zy.design_pattern.interceptor_pattern.example1;


public class AuditInterceptor implements Interceptor{
    @Override
    public Response intercept(TargetInvocation targetInvocation) {
        if(targetInvocation.getTarget() == null) {
            throw new IllegalArgumentException("Target is null");
        }
        System.out.println("Audit Succeeded ");
        return targetInvocation.invoke();
    }
}