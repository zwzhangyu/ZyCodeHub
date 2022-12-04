package com.zy.design_pattern.interceptor.example1;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TargetInvocation {
    private final List<Interceptor> interceptorList = new ArrayList<>();
    private Iterator<Interceptor> interceptors;
    private Target target;
    private Request request;

    public Response invoke() {
        if (interceptors.hasNext()) {
            Interceptor interceptor = interceptors.next();
            // 此处是整个算法的关键，这里会递归调用invoke()
            interceptor.intercept(this);//2
        }
        return target.execute(request);
    }

    public void addInterceptor(Interceptor interceptor) {
        // 添加新的Interceptor到TargetInvocation中
        interceptorList.add(interceptor);
        interceptors = interceptorList.iterator();
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}