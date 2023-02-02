package com.zy.design_pattern.callback.example1.demo5;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Service;

//注入IOC容器中
@Service("myListener")
public class MyListener implements ApplicationListener<ApplicationEvent> {
    //调用ApplicationContext.publishEvent方法时会触发执行该方法
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        //判断事件为MyEvent时候执行
        if (event instanceof MyEvent) {
            //强制转换
            MyEvent evt = (MyEvent) event;
            //执行自定义事件中的自定义方法
            evt.myevent();
        }
        //如果容器关闭时，触发
        if (event instanceof ContextClosedEvent) {
            ContextClosedEvent cce = (ContextClosedEvent) event;
            System.out.println("#####################");
            System.out.println("容器关闭");
            System.out.println(cce);
            System.out.println("#####################");
        }
        //容器刷新时候触发
        if (event instanceof ContextRefreshedEvent) {
            ContextRefreshedEvent cre = (ContextRefreshedEvent) event;
            System.out.println("#####################");
            System.out.println("容器刷新");
            System.out.println(cre);
            System.out.println("#####################");
        }
        //容器启动的时候触发
        if (event instanceof ContextStartedEvent) {
            ContextStartedEvent cse = (ContextStartedEvent) event;
            System.out.println("#####################");
            System.out.println("容器启动");
            System.out.println(cse);
            System.out.println("#####################");
        }
        //容器停止时候触发
        if (event instanceof ContextStoppedEvent) {
            ContextStoppedEvent cse = (ContextStoppedEvent) event;
            System.out.println("#####################");
            System.out.println("容器停止");
            System.out.println(cse);
            System.out.println("#####################");
        }
    }

}