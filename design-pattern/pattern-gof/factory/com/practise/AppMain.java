package com.practise;

public class AppMain {
    public static void main(String args[]) {
        Pizza pizza = PizzaStore.orderPiza("VEGI");

        pizza.prepare();
        pizza.bake();
        pizza.cut();
    }
}
