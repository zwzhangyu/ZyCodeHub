package com.zy.threadlocal.ali_ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*******************************************************
 * Created by ZhangYu on 2024/10/20
 * Description :
 * History   :
 *******************************************************/
public class Test1 {
    public static void main(String[] args) {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        // 设置上下文信息
        context.set("上下文信息");
        // 在线程池中使用时，TTL 会自动传递上下文
        ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(10));
        executorService.submit(() -> {
            // 子线程中可以获取到上下文信息
            System.out.println(context.get());
        });
        executorService.submit(() -> {
            // 子线程中可以获取到上下文信息
            System.out.println(context.get());
        });

    }
}
