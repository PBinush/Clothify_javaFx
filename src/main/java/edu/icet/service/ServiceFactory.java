package edu.icet.service;

import edu.icet.Repository.custom.impl.CustomerDaoImpl;
import edu.icet.Repository.custom.impl.EmployeeDaoImpl;
import edu.icet.service.custom.impl.*;
import edu.icet.util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance==null?instance=new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceTpe(ServiceType type){
        switch (type){
            case USER:return (T) new UserServiceImpl();
            case SUPPLIER:return (T) new SupplierServiceImpl();
            case PRODUCT:return (T) new ProductServiceImpl();
            case CUSTOMERS:return (T) new CustomerServiceImpl();
            case EMPLOYEE:return (T) new EmployeeServiceImpl();
            case ORDER_DETAILS:return (T) new OrderDetailsServiceImpl();
            case ORDER:return (T) new OrderServiceImpl();
        }
        return null;
    }
}
