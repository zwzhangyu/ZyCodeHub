package com.zy.design_pattern.observer.example1;

public interface Observer {
    /**
     * @param temp     temperature温度
     * @param humidity (空气中的)湿度
     * @param pressure 大气压
     */
    void update(float temp, float humidity, float pressure);
}