package edu.icet.service.custom;

import edu.icet.dto.Customer;
import edu.icet.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {
    boolean saveCustomer(Customer customer);
    List<Customer> getAllCustomers();
    String genarateId();
    Customer getCustomerById(String id);
}
