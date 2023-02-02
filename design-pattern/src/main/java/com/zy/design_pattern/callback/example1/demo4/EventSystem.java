package com.zy.design_pattern.callback.example1.demo4;

public class EventSystem {
    MyButton mybutton = new MyButton();

    public EventSystem() {
        mybutton.setName("Button 1");
        mybutton.RegisterListener(new ActionListener() {
            @Override
            public void doAction(MyEvent e) {
                ClickEvent ce = (ClickEvent) e;
                String name = ((MyButton) ce.getSource()).getName();
                System.out.println(name + " Clicked.");
                System.out.print("Event time: ");
                System.out.print(ce.getEventTime());
            }
        });
    }

    public void run() {
        mybutton.click();
    }

    public static void main(String[] args) {
        EventSystem es = new EventSystem();
        es.run();
    }
}
