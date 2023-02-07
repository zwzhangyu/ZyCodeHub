package com.zy.design_pattern.adapter;

public class IPhone {

    //手机的充电功能，只能接收5V的电压
    void charging(Voltage5V voltage5V) {
        //获取接入的电流
        int output = voltage5V.output5V();
        if (output > 5) {
            System.out.println("电压过高");
        } else {
            System.out.println("手机正在充电，当前电压" + output + "伏");
        }
    }
}