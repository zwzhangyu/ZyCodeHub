package com.zy.design_pattern.callback.example1.demo4;

public class MyButton {
    private String name;

    private ActionListener al = null;

    public MyButton() {
        this("NoName");
    }

    public MyButton(String name) {
        this.name = name;
    }

    public void RegisterListener(ActionListener al) {
        this.al = al;
    }

    public void click() {
        if (al != null) {
            al.doAction(new ClickEvent(this));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
