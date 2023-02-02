package com.zy.design_pattern.callback.example1.demo4;

public abstract class MyEvent {
    private Object source;
    private Object arg;
    private long time;

    public MyEvent(Object src) {
        this(src, null);
    }

    public MyEvent(Object src, Object arg) {
        this.source = src;
        this.arg = arg;
        this.time = System.nanoTime();
    }

    public Object getArg() {
        return arg;
    }

    public Object getSource() {
        return source;
    }

    public long getEventTime() {
        return time;
    }

}