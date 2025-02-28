package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.CustomerDao;
import edu.icet.dto.Customer;
import edu.icet.entity.CustomerEntity;
import edu.icet.service.custom.CustomerService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    final CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMERS);

    @Override
    public boolean saveCustomer(Customer customer) {
        CustomerEntity map = new ModelMapper().map(customer, CustomerEntity.class);
        map.setId(genarateId());
        if (customerDao.save(map)){
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        for (CustomerEntity entity : customerDao.getAll()) {
            customers.add(
                    new ModelMapper().map(entity,Customer.class)
            );
        }
        return customers;
    }

    @Override
    public String genarateId() {
        String lastId = customerDao.getLastId();
        if (lastId == null || lastId.isEmpty()) {
            lastId = "C000";
        }

        int i = Integer.parseInt(lastId.substring(1));
        i++;

        String id = String.format("C%03d", i);
        System.out.println("Next Customer ID: " + id);
        return id;
    }

    @Override
    public Customer getCustomerById(String id) {
        CustomerEntity customerById = customerDao.getCustomerById(id);
        return new ModelMapper().map(customerById,Customer.class);
    }
}
