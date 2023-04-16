package com.zy.design_pattern.callback.example2.synchronization;

import java.util.function.Consumer;

/**
 * 同步场景下匿名内部类的方式实现回调
 *
 * @author zhangyu
 * @date 2023/4/16
 */
public class AnonymousClassCallback {

    public static void main(String[] args) {
        performAction(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }

    public static void performAction(Consumer<String> consumer) {
        System.out.println("执行特定的业务逻辑");
        consumer.accept("回调代码被执行");
    }

}