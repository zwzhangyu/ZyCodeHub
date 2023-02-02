package com.zy.design_pattern.strategy.example2;


public class ConcreteStrategyA extends Strategy {
    @Override
    public void algorithmInterface() {
        System.out.println("执行算法策略A");
    }
}
