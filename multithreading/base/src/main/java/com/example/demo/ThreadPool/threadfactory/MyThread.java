package com.example.demo.ThreadPool.threadfactory;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/***
 *
 * 配合ThreadFactory构建自定义的线程
 * @author ZhangYu
 * @date 2021/9/9
 */
@Slf4j
public class MyThread  extends  Thread {

    /** 线程名称  **/
    private static  final  String NAME="MyThread";

    /** 累计创建的线程数量  **/
    private static  final  AtomicInteger HAS_CREATED =new AtomicInteger();

    /** 累计存活的线程数量  **/
    private static  final  AtomicInteger HAS_ALIVE =new AtomicInteger();


    public MyThread(Runnable target) {
        this(target,NAME);
    }

    public MyThread(Runnable target, String name) {
        super(target, name+HAS_CREATED.incrementAndGet());
        log.info("线程-"+getName()+"-创建完成");
    }


    @Override
    public void run() {
        log.info("线程-"+getName()+"-开始运行");
        try {
            HAS_ALIVE .incrementAndGet();
            super.run();
        }finally {
            HAS_ALIVE .decrementAndGet();
            log.info("线程-"+getName()+"-退出");
        }

    }
}
