package com.zy.spring.guava_retry;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.zy.spring.spring_retry.simple.RetryDemoTask;
import org.springframework.remoting.RemoteAccessException;

import java.util.concurrent.TimeUnit;

/**
 * guava-retrying测试
 *
 * @author zhangyu
 */
public class GuavaUserService {

    public static void main(String[] args) {
        // RetryerBuilder 构建重试实例 retryer,可以设置重试源且可以支持多个重试源，可以配置重试次数或重试超时时间，以及可以配置等待时间间隔
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfExceptionOfType(RemoteAccessException.class)//设置异常重试源
                .retryIfResult(res -> !res)  //设置根据结果重试
                .withWaitStrategy(WaitStrategies.fixedWait(3, TimeUnit.SECONDS)) //设置等待间隔时间
                .withStopStrategy(StopStrategies.stopAfterAttempt(3)) //设置最大重试次数
                .build();
        try {
            retryer.call(() -> RetryDemoTask.retryTask("abc"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
