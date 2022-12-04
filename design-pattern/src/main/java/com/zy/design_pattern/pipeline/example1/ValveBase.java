package com.zy.design_pattern.pipeline.example1;

/**
 * 阀门的基础实现
 *
 * @author zhangyu
 * @date 2022/12/4
 **/
public abstract class ValveBase implements Valve {
    public Valve next;

    public Valve getNext() {
        return next;
    }

    public void setNext(Valve v) {
        next = v;
    }

    public abstract void invoke(String s);
}