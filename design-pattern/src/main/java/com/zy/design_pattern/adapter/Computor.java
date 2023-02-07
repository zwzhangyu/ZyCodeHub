package com.zy.design_pattern.adapter;

public class Computor {

    //电脑的充电功能，只能接收12V的电压
    void charging(Voltage12V voltage12V) {
        //获取接入的电流
        int output = voltage12V.output12V();
        if (output == 12) {
            System.out.println("电脑正在充电，当前电压" + output + "伏");
        } else {
            System.out.println("电压不符");
        }
    }
}