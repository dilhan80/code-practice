package com.test._interface.concept;

public class Dog implements Animal, Mamals{
    @Override
    public void hello() {
        System.out.println("Dog");
    }
}
