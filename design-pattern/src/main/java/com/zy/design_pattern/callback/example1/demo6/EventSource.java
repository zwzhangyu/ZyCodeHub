package com.zy.design_pattern.callback.example1.demo6;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Vector;

public class EventSource {
    //监听器列表
    private Vector<EventListener> listenerList = new Vector<>();

    //注册监听器
    public void addListener(EventListener eventListener) {
        listenerList.add(eventListener);
    }

    //撤销注册
    public void removeListener(EventListener eventListener) {
        listenerList.remove(eventListener);
    }

    //接收外部事件,通知者
    public void notifyListenerList(EventObject event) {
        for (EventListener eventListener : listenerList) {
        }
    }

}
