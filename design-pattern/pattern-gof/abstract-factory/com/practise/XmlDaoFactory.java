package com.practise;

public class XmlDaoFactory extends DaoAbastractFactory{
    @Override
    public Dao createDao(String type) {
        Dao dao = null;
        if("emp".equals(type)){
            dao = new XmlEmpDao();
        } else if("dept".equals(type)) {
            dao = new XmlDeptDao();
        }
        return null;
    }
}
