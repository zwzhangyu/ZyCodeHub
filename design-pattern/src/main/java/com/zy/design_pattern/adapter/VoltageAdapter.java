package com.zy.design_pattern.adapter;

public class VoltageAdapter extends Voltage220V implements Voltage5V, Voltage12V {

    //将220V电压转换成5V电压
    @Override
    public int output5V() {
        //获取源电压数据，即适配器先连接220V的插座
        int output = output220V();
        //这里就是模拟适配进行了内部电压转换，将220V电压转换成了5V
        output = 5;
        return output;
    }

    //将220V电压转换成12V电压
    @Override
    public int output12V() {
        //获取源电压数据，即适配器先连接220V的插座
        int output = output220V();
        //这里就是模拟适配进行了内部电压转换，将220V电压转换成了12V
        output = 12;
        return output;
    }
}