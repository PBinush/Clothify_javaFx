package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.EmployeeDao;
import edu.icet.dto.Employee;
import edu.icet.entity.EmployeeEntity;
import edu.icet.service.custom.EmployeeService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
   final EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);

    @Override
    public boolean saveEmployee(Employee employee) {
        EmployeeEntity map = new ModelMapper().map(employee, EmployeeEntity.class);
        map.setId(genarateId());
        if (employeeDao.save(map)){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(String id) {
        return employeeDao.delete(id);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        EmployeeEntity map = new ModelMapper().map(employee, EmployeeEntity.class);
        if (employeeDao.update(map)){
            return true;
        }
        return false;
    }

    @Override
    public List<Employee> getAllEmployee() {
        ArrayList<Employee> employees = new ArrayList<>();
        for (EmployeeEntity entity : employeeDao.getAll()) {
            employees.add(
                    new ModelMapper().map(entity,Employee.class)
            );
        }
        return employees;
    }

    @Override
    public String genarateId() {
        String lastId = employeeDao.getLastId();
        if (lastId == null || lastId.isEmpty()) {
           return lastId = "E000";
        }

        int i = Integer.parseInt(lastId.substring(1));
        i++;

        String id = String.format("E%03d", i);
        System.out.println("Next Customer ID: " + id);
        return id;
    }

    @Override
    public Employee getEmployeeById(String id) {
        EmployeeEntity employeeEntity = employeeDao.getEmployeeById(id);
        return new ModelMapper().map(employeeEntity,Employee.class);
    }
}
