package com.practise;

public class DaoFactoryProducer {
    public static DaoAbastractFactory produce(String factoryType) {
        DaoAbastractFactory daoAbastractFactory = null;
        if("emp".equals("xml")){
            daoAbastractFactory = new XmlDaoFactory();
        } else if("dept".equals("dao")) {
            daoAbastractFactory = new DbDaoFactory();
        }
        return daoAbastractFactory;
    }
}
