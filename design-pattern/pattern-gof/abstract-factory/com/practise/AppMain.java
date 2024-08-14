package com.practise;

public class AppMain {
    public static void main(String arg[]) {
        DaoAbastractFactory daoAbastractFactory = DaoFactoryProducer.produce("xml");
        daoAbastractFactory.createDao("emp");
    }
}
