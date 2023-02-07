package com.zy.design_pattern.responsibility_chain.example1;

public abstract class Handler {

    protected Handler successor;

    public Handler(Handler handler) {
        this.successor = handler;
    }

    protected abstract void handleRequest(Request request);
}
