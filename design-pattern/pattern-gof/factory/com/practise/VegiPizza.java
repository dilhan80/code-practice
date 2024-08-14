package com.practise;

public class VegiPizza implements Pizza{
    @Override
    public void prepare() {
        System.out.println("Preparing vegi pizza.");
    }

    @Override
    public void bake() {
        System.out.println("Baking vegi pizza.");
    }

    @Override
    public void cut() {
        System.out.println("Cutting vegi pizza.");
    }
}
