package edu.icet.Repository.custom.impl;

import edu.icet.Repository.custom.EmployeeDao;
import edu.icet.entity.EmployeeEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity employeeEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(employeeEntity);
            transaction.commit();
            System.out.println(employeeEntity);
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
    public boolean update(EmployeeEntity employeeEntity) {
        return false;
    }

    @Override
    public EmployeeEntity search(String id) {
        return null;
    }

    @Override
    public String getLastId() {
        return "";
    }
}
