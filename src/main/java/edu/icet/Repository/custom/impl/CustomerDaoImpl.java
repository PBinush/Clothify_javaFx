package edu.icet.Repository.custom.impl;

import edu.icet.Repository.custom.CustomerDao;
import edu.icet.db.DBConnection;
import edu.icet.entity.CustomerEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            CustomerEntity customerEntity = session.get(CustomerEntity.class, id);
            session.delete(customerEntity);
            transaction.commit();
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
    public boolean update(CustomerEntity customerEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(customerEntity);
            transaction.commit();
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
    public CustomerEntity search(String id) {
        return null;
    }

    @Override
    public String getLastId() {
        Connection connection = DBConnection.getInstance().getConnection();
        String lastId = null;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT id FROM customers ORDER BY id DESC LIMIT 1");
            if (resultSet.next()) {
                lastId = resultSet.getString("id");
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastId;
    }


    @Override
    public List<CustomerEntity> getAll() {
        Session session = HibernateUtil.getSession();
        try {
            List<CustomerEntity> fromCustomers = session.createQuery("FROM CustomerEntity", CustomerEntity.class).list();
            return fromCustomers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public CustomerEntity getCustomerById(String id) {
        Session session = HibernateUtil.getSession();
        try {
            Query<CustomerEntity> query = session.createQuery("FROM CustomerEntity WHERE id = :customerId", CustomerEntity.class);
            query.setParameter("customerId", id);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
