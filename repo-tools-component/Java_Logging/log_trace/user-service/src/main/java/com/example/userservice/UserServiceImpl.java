package com.example.userservice;


import com.example.common.TraceThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl   implements  UserService{

    public static final TraceThreadPoolExecutor threadPool = new TraceThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));

    @Async
    @Override
    public void sendMsgByAsy() {
        log.info("通过Async线程执行异步任务");
    }
    @Override
    public void sendMsgByThreadPool() {
        threadPool.execute(()->log.info("线程池执行异步任务"));
    }
}
