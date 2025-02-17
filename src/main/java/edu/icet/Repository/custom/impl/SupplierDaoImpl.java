package edu.icet.Repository.custom.impl;

import edu.icet.Repository.custom.SupplierDao;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SupplierDaoImpl implements SupplierDao {
    
    @Override
    public boolean save(SupplierEntity supplierEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(supplierEntity);
            transaction.commit();
            System.out.println(supplierEntity);
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
    public boolean update(SupplierEntity supplierEntity) {
        return false;
    }

    @Override
    public SupplierEntity search(String id) {
        return null;
    }

    @Override
    public String getLastId() {
        return "";
    }
}
