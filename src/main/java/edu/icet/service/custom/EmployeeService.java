package edu.icet.service.custom;

import edu.icet.dto.Employee;
import edu.icet.service.SuperService;

public interface EmployeeService extends SuperService {
    boolean saveEmployee(Employee employee);
}
