package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.CustomerDao;
import edu.icet.dto.Customer;
import edu.icet.entity.CustomerEntity;
import edu.icet.service.custom.CustomerService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public boolean saveCustomer(Customer customer) {
        String id = "C001";
        CustomerEntity map = new ModelMapper().map(customer, CustomerEntity.class);
        map.setId(id);
        CustomerDao daoType = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMERS);
        if (daoType.save(map)){
            return true;
        }
        return false;
    }
}
