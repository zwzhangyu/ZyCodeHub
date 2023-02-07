package com.zy.design_pattern.adapter;

/**
 * 类适配器模式主要是Adapter类，通过继承 src类，实现 dst 类接口，完成src->dst的适配。
 * 在下面实例中，我们需要完成手机充电，由于不能直接使用220V电压，因此需要进行转换，
 * 创建一个适配器将220V电压转换为5V电压，然后手机进行充电。
 */
public class Client {
    public static void main(String[] args) {
        //手机充电
        IPhone iPhone = new IPhone();
        //购置一个适配器
        VoltageAdapter voltageAdapter = new VoltageAdapter();
        //手机使用适配器充电
        iPhone.charging(voltageAdapter);
        //电脑充电
        Computor computor = new Computor();
        computor.charging(voltageAdapter);
    }
}
