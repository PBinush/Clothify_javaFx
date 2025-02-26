package edu.icet.service.custom;

import edu.icet.dto.Customer;
import edu.icet.service.ServiceFactory;
import edu.icet.service.SuperService;

public interface CustomerService extends SuperService {
    boolean saveCustomer(Customer customer);
}
