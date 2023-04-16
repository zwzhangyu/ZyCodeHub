package com.zy.design_pattern.callback.example2.synchronization;

/**
 * 同步场景下匿名内部类的方式实现回调（Lambda写法）
 *
 * @author zhangyu
 * @date 2023/4/16
 */
public class LambdaCallback {
    public static void main(String[] args) {
        performAction(() -> System.out.println("回调代码被执行"));
    }

    public static void performAction(Runnable runnable) {
        System.out.println("执行特定的业务逻辑");
        runnable.run();
    }
}
