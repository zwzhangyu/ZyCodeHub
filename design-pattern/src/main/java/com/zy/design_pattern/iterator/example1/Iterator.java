package com.zy.design_pattern.iterator.example1;

public interface Iterator<Item> {
    Item next();

    boolean hasNext();
}