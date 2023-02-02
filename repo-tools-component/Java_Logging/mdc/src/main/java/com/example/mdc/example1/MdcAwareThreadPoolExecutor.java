package com.example.mdc.example1;

import org.apache.log4j.MDC;
import org.apache.logging.log4j.ThreadContext;

import java.util.concurrent.*;

public class MdcAwareThreadPoolExecutor extends ThreadPoolExecutor {

    public MdcAwareThreadPoolExecutor(int corePoolSize,
                                      int maximumPoolSize,
                                      long keepAliveTime,
                                      TimeUnit unit,
                                      BlockingQueue<Runnable> workQueue,
                                      ThreadFactory threadFactory,
                                      RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    /**
     * 线程执行完毕，清理MDC数据
     * @param r 线程
     * @param t 异常
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        MDC.clear();
        MDC.clear();
        ThreadContext.clearAll();
    }
}