package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.EmployeeDao;
import edu.icet.dto.Employee;
import edu.icet.entity.EmployeeEntity;
import edu.icet.service.custom.EmployeeService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public boolean saveEmployee(Employee employee) {
        String id = "E001";
        EmployeeEntity entity = new ModelMapper().map(employee, EmployeeEntity.class);
        entity.setId(id);
        EmployeeDao daoType = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        if (daoType.save(entity)){
            return true;
        }
        return false;
    }
}
