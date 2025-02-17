package edu.icet.Repository.custom.impl;

import edu.icet.Repository.custom.CustomerDao;
import edu.icet.entity.CustomerEntity;
import edu.icet.entity.ProductEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity customerEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(customerEntity);
            transaction.commit();
            System.out.println(customerEntity);
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
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

    @Override
    public String getLastId() {
        return "";
    }


}
