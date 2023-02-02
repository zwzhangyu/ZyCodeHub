package com.zy.design_pattern.callback.example1.demo3;

public class MyWindow {
    public static String arg = "This is my window.";

    private Button bt1 = new Button("Button 1");

    private Button bt2 = new Button("Button 2");

    private class Btn1ClickHandler implements ClickHandler {
        @Override
        public void onClick() {
            System.out.println(bt1.getName() + " Click!");
        }
    }

    private ClickHandler btn2ClickHandler = new ClickHandler() {
        @Override
        public void onClick() {
            System.out.println(bt2.getName() + " Click!");
            bt1.setName(arg);
        }
    };

    public MyWindow() {
        bt1.registeHandler(new Btn1ClickHandler());
        bt2.registeHandler(btn2ClickHandler);
    }

    public void run() {
        bt1.click();
        bt2.click();
        System.out.println(bt1.getName());
        System.out.println(bt2.getName());
    }

    public static void main(String[] args) {
        MyWindow mw = new MyWindow();
        mw.run();


    }
}
