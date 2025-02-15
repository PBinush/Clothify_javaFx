package edu.icet.Repository.custom.impl;

import edu.icet.Repository.custom.EmployeeDao;
import edu.icet.entity.EmployeeEntity;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity employeeEntity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(EmployeeEntity employeeEntity) {
        return false;
    }

    @Override
    public EmployeeEntity search(String id) {
        return null;
    }
}
