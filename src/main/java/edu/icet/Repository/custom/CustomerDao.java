package edu.icet.Repository.custom;

import edu.icet.Repository.CrudDao;
import edu.icet.entity.CustomerEntity;

import java.util.List;

public interface CustomerDao extends CrudDao<CustomerEntity> {
    List<CustomerEntity> getAll();
    CustomerEntity getCustomerById(String id);
}
