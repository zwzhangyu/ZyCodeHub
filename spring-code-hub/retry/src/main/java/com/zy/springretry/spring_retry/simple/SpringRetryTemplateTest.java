package com.zy.springretry.spring_retry.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * spring-retry 重试框架
 *
 * @author zhangyu
 */
@Slf4j
public class SpringRetryTemplateTest {

    /**
     * 重试间隔时间ms,默认1000ms
     */
    private long fixedPeriodTime = 1000L;
    /**
     * 最大重试次数,默认为3
     */
    private int maxRetryTimes = 3;
    /**
     * 表示哪些异常需要重试,key表示异常的字节码,value为true表示需要重试
     */
    private Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();


    @Test
    public void test() {
        exceptionMap.put(RemoteAccessException.class, true);
        // 构建重试模板实例
        RetryTemplate retryTemplate = new RetryTemplate();
        // 设置重试回退操作策略，主要设置重试间隔时间
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(fixedPeriodTime);
        // 设置重试策略，主要设置重试次数
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(maxRetryTimes, exceptionMap);
        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        Boolean execute = retryTemplate.execute(
                // RetryCallback
                retryContext -> {
                    boolean b = RetryDemoTask.retryTask("abc");
                    log.info("调用的结果:{}", b);
                    return b;
                },
                retryContext -> {
                    //RecoveryCallback
                    log.info("已达到最大重试次数或抛出了不重试的异常~~~");
                    return false;
                }
        );
        log.info("执行结果:{}", execute);
    }

}