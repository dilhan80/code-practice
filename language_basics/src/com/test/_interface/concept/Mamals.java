package com.test._interface.concept;

public interface Mamals {
    default void hello() { System.out.println("Mamals"); }
    static void getSound(){
        System.out.println("default mamal sound");
    }
}
