package com.test._interface.concept;

public interface Animal {
    default void hello() { getName(); }
    private void getName() {
        System.out.println("Animal");
    }

}
