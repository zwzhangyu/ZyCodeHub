package com.zy.design_pattern.callback.example2.asynchronous;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture callback
 *
 * @author zhangyu
 * @date 2023/4/16
 */
public class CompletableFutureCallback {
    public static void main(String[] args) throws Exception {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行业务代码");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("执行业务代码结束");
            return "ok";
        });

        CompletableFuture<String> execution = completableFuture
                .thenApply(s -> s + "回调被执行");
        System.out.println("main线程执行代码");
        System.out.println(execution.get());
    }
}
