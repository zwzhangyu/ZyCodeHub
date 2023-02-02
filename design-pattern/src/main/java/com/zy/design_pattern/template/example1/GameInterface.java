package com.zy.design_pattern.template.example1;

public interface GameInterface {

    void initialize();

    void startPlay();

    void endPlay();

    default void play() {

        //初始化游戏
        initialize();

        //开始游戏
        startPlay();

        //结束游戏
        endPlay();
    }
}
