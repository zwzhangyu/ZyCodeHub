package com.zy.design_pattern.command.example1;

/**
 * 灯光，受命令控制的组件
 */
public class Light {
    public void on() {
        System.out.println("Light is on!");
    }

    public void off() {
        System.out.println("Light is off!");
    }
}