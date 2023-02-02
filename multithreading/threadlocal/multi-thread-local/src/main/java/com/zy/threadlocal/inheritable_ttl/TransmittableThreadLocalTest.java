package com.zy.threadlocal.inheritable_ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.testng.annotations.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * TransmittableThreadLocal（TTL）测试
 *
 * @author zhangyu
 */
public class TransmittableThreadLocalTest {


    @Test
    public void transmittableThreadLocal() {
        TransmittableThreadLocal<Integer> tl = new TransmittableThreadLocal<>();
        tl.set(6);
        // 第一次输出：6
        System.out.println("父线程获取数据：" + tl.get());
        // 使用 jdk 的 Executors 工具创建一个线程池
        // 注意，这个线程池里只有一个线程
        Executor realPool = Executors.newFixedThreadPool(1);
        // 使用 TtlExecutors 创建一个 Ttl 框架封装的线程池
        Executor pool = TtlExecutors.getTtlExecutor(realPool);
        // 使用线程池跑一个任务
        pool.execute(() -> {
            Integer i = tl.get();
            // 第二次输出：6
            System.out.println("第一次获取数据：" + i);
        });
        // 修改一下 tl 里的值，并再跑一次任务
        tl.set(7);
        pool.execute(() -> {
            Integer i = tl.get();
            // 第三次输出：7 父线程的更新能够在子线程中同步
            System.out.println("第二次获取数据：" + i);
        });
    }
}
