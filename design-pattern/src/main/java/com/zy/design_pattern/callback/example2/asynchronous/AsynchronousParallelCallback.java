package com.zy.design_pattern.callback.example2.asynchronous;

/**
 * 异步并行回调
 *
 * @author zhangyu
 * @date 2023/4/16
 */
public class AsynchronousParallelCallback {

    public void performAsynchronousAction(Runnable runnable) {

        new Thread(() -> {
            System.out.println("执行异步操作代码");
            // 创建一个新的线程执行回调
            new Thread(runnable).start();
        }).start();
    }

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("回调代码被执行");
        AsynchronousParallelCallback callback = new AsynchronousParallelCallback();
        callback.performAsynchronousAction(runnable);
    }
}
