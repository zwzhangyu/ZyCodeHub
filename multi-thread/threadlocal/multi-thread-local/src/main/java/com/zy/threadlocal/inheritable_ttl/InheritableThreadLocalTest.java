package com.zy.threadlocal.inheritable_ttl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 在线程池的情况下，第一次创建线程的时候会从父线程中 copy inheritableThreadLocals 中的数据，
 * 所以第一个任务成功拿到了父线程设置的”i am a inherit parent“，第二个任务执行的时候复用了第一个任务的线程，
 * 并不会触发复制父线程中的 inheritableThreadLocals 操作，所以即使在主线程中设置了新的值，也会不生效。
 * 同时 get () 方法是直接操作 inheritableThreadLocals 这个变量的，所以就直接拿到了第一个任务设置的值。
 *
 * @author zhangyu
 */
public class InheritableThreadLocalTest {
    static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        inheritableThreadLocal.set("父线程设置值");
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // 子线程A读取父线程的值：父线程设置值
                System.out.println("子线程A读取父线程的值：" + inheritableThreadLocal.get());
                // 子线程中设置新的值
                inheritableThreadLocal.set("子线程A设置值");
            }
        });
        TimeUnit.SECONDS.sleep(2);
        // 主线程设置新的值，注意这里的重新赋值在子线程中不会再次获取到
        inheritableThreadLocal.set("父线程设置新的值");
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // 子线程B读取父线程的值：子线程A设置值
                System.out.println("子线程B读取父线程的值：" + inheritableThreadLocal.get());
            }
        });
        TimeUnit.SECONDS.sleep(2);
    }
}
