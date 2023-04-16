package com.zy.design_pattern.callback.example2.asynchronous;

/**
 * 异步回调实例
 *
 * @author zhangyu
 * @date 2023/4/16
 */
public class AsynchronousCallback {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("回调代码被执行");
        AsynchronousCallback asynchronousCallback = new AsynchronousCallback();
        asynchronousCallback.performAsynchronousAction(runnable);
    }

    public void performAsynchronousAction(Runnable runnable) {
        new Thread(() -> {
            System.out.println("执行异步操作代码");
            runnable.run();
        }).start();
    }

}