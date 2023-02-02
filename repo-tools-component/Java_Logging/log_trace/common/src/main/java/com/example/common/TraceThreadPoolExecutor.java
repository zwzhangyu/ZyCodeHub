package com.example.common;

import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author zhangyu
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

    @Override
    public void execute(Runnable command) {
        // 提交者的本地变量
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        super.execute(()->{
            if (contextMap != null) {
                // 如果提交者有本地变量，任务执行之前放入当前任务所在的线程的本地变量中
                MDC.setContextMap(contextMap);
            }
            try {
                command.run();
            } finally {
                // 任务执行完，清除本地变量，以防对后续任务有影响
                MDC.clear();
            }
        });
    }
    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }


    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }


}