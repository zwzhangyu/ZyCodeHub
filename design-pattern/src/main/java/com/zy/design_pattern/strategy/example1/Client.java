package com.zy.design_pattern.strategy.example1;

public class Client {


    public static void main(String[] args) {
        Context context = new Context(new ConcreteStrategyA());
        context.contextInterface();
    }
}
