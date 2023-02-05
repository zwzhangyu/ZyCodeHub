package com.zy.design_pattern.visitor.example1;

public interface Visitor {
    void visit(Customer customer);

    void visit(Order order);

    void visit(Item item);
}