package com.zy.design_pattern.strategy.example2;

/**
 * @author zhangyu
 */
public class ContextFactory {
    private Strategy strategy;
    public ContextFactory(String type) {
        //在Context中根据业务类型，将处理逻辑转移到这里，对调用方透明
        switch (type) {
            case "A":
                strategy = new ConcreteStrategyA();
                break;
            case "B":
                strategy = new ConcreteStrategyB();
                break;
            default:
                strategy = new ConcreteStrategy();
        }
    }

    public void contextInterface() {
        //根据具体的策略对象，调用其算法的方法
        strategy.algorithmInterface();
    }
}
