package edu.icet.Repository;

import edu.icet.Repository.custom.impl.*;
import edu.icet.util.DaoType;

public class DaoFactory {

    private static DaoFactory instance;

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance==null?instance = new DaoFactory():instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType type){
        switch (type){
            case USER : return (T) new UserDaoImpl();
            case SUPPLIER:return (T) new SupplierDaoImpl();
            case PRODUCT:return (T) new ProductDaoImpl();
            case CUSTOMERS:return (T) new CustomerDaoImpl();
            case EMPLOYEE:return (T) new EmployeeDaoImpl();
        }
        return null;
    }
}
