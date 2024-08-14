package com.practise;

public class PizzaStore {
    public static Pizza orderPiza(String pizzaType) {
        switch (pizzaType) {
            case "VEGI" : {
                return new CheesePizza();
            }
            case "CHEEZE" :{
                return new VegiPizza();
            }
            default:{
                return null;
            }
        }
    }
}
