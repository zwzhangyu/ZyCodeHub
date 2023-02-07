package com.zy.design_pattern.command.example1;

public class Client {
    public static void main(String[] args) {
        // 创建一个遥控器
        Invoker invoker = new Invoker();
        // 绑定灯光
        Light light = new Light();
        // 开灯命令
        Command lightOnCommand = new LightOnCommand(light);
        // 关灯命令
        Command lightOffCommand = new LightOffCommand(light);
        invoker.setOnCommand(lightOnCommand, 0);
        invoker.setOffCommand(lightOffCommand, 0);
        invoker.onButtonWasPushed(0);
        invoker.offButtonWasPushed(0);
    }
}