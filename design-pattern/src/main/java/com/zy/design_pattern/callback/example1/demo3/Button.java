package com.zy.design_pattern.callback.example1.demo3;

public class Button {
    private String name;
    private ClickHandler clickHandler = null;

    public Button() {
        this.name = "NoName";
    }

    public Button(String name) {
        this.name = name;
    }

    public void click() {
        if (clickHandler != null) {
            clickHandler.onClick();
        }
    }

    public void registeHandler(ClickHandler handler) {
        clickHandler = handler;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
