package edu.icet.service.custom;

import edu.icet.dto.Employee;
import edu.icet.service.SuperService;
import java.util.List;

public interface EmployeeService extends SuperService {
    boolean saveEmployee(Employee employee);
    boolean deleteEmployee(String id);
    boolean updateEmployee(Employee employee);
    List<Employee> getAllEmployee();
    String genarateId();
    Employee getEmployeeById(String id);
}
