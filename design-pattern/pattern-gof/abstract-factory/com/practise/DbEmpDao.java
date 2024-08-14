package com.practise;

public class DbEmpDao implements Dao{
    @Override
    public void save() {
        System.out.println("Saving employee to DB");
    }
}
