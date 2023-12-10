package com.zy.core_java.completable;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {

    @Test
    public void test1() {
        ExecutorService testThreadPool = Executors.newFixedThreadPool(10);
        ResultDTO resultDTO = new ResultDTO();
        Map<Object, Object> resultMap = new HashMap<>();
        CompletableFuture<Void> baseInfoFuture = CompletableFuture.runAsync(() -> {
            System.out.println("获取商品验机评估报告任务当前线程" + Thread.currentThread().getId());
            //获取报告rpc接口逻辑
            resultMap.put("baseInfo", "");
        }, testThreadPool).exceptionally(e -> {
            System.out.println(e.getMessage());
            return null;
        });

    }

}
