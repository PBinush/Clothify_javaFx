package edu.icet.Repository.custom;

import edu.icet.Repository.CrudDao;
import edu.icet.entity.EmployeeEntity;
import java.util.List;

public interface EmployeeDao extends CrudDao<EmployeeEntity> {
    List<EmployeeEntity> getAll();
    EmployeeEntity getEmployeeById(String id);
}
