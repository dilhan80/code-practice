package com.practise;

public class DbDaoFactory extends DaoAbastractFactory{
    @Override
    public Dao createDao(String type) {
        Dao dao = null;
        if("emp".equals(type)){
            dao = new DbEmpDao();
        } else if("dept".equals(type)) {
            dao = new DbDeptDao();
        }
        return null;
    }
}
