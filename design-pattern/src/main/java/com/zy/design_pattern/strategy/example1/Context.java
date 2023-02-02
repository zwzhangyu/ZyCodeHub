package com.zy.design_pattern.strategy.example1;

public class Context {
    private Strategy strategy;
    public Context(Strategy strategy) {
        //初始化，传入具体的策略对象
        this.strategy = strategy;
    }
    public void contextInterface() {
        //根据具体的策略对象，调用其算法的方法
        strategy.algorithmInterface();
    }
}
