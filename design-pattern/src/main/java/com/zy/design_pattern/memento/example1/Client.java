package com.zy.design_pattern.memento.example1;

public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        // 设置状态
        originator.setState("State #1");
        originator.setState("State #2");
        // 保存当前状态
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");

        System.out.println("Current State: " + originator.getState()); // 4
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState()); // 2
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState()); // 3
    }
}