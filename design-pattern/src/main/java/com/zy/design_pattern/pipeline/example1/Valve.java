package com.zy.design_pattern.pipeline.example1;

/**
 * 阀门接口
 *
 * @author zhangyu
 * @date 2022/12/4
 **/
public interface Valve {

    /**
     * 获取下一个阀门节点
     */
    Valve getNext();

    /**
     * 设置下一个阀门节点
     */
    void setNext(Valve v);

    /**
     * 当前阀门处理逻辑
     */
    void invoke(String s);

}
