package com.zy.spring.spring_retry.spring;

import com.zy.spring.spring_retry.simple.RetryDemoTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * spring-retry 重试框架
 *
 * @author zhangyu
 */
@Service
@Slf4j
public class UserService {


    @Retryable(value = {RemoteAccessException.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 2))
    public boolean getUser(String param) {
        return RetryDemoTask.retryTask(param);
    }

    @Recover
    public boolean myRecover(Exception e, String param) {
        log.error("达到最大重试次数,或抛出了一个没有指定进行重试的异常:", e);
        return false;
    }

}
