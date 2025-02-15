package edu.icet.Repository.custom.impl;

import edu.icet.Repository.custom.CustomerDao;
import edu.icet.entity.CustomerEntity;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity customerEntity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(CustomerEntity customerEntity) {
        return false;
    }

    @Override
    public CustomerEntity search(String id) {
        return null;
    }
}
