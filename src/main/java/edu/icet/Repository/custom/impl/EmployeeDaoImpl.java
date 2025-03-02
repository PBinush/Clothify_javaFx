package edu.icet.Repository.custom.impl;

import edu.icet.Repository.custom.EmployeeDao;
import edu.icet.db.DBConnection;
import edu.icet.entity.EmployeeEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            EmployeeEntity employeeEntity = session.get(EmployeeEntity.class, id);
            session.delete(employeeEntity);
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
    public boolean update(EmployeeEntity employeeEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(employeeEntity);
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
    public EmployeeEntity search(String id) {
        return null;
    }

    @Override
    public String getLastId() {
        Connection connection = DBConnection.getInstance().getConnection();
        String lastId = null;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT id FROM employee ORDER BY id DESC LIMIT 1");
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
    public List<EmployeeEntity> getAll() {
        Session session = HibernateUtil.getSession();
        try {
            List<EmployeeEntity> fromEmployee = session.createQuery("FROM EmployeeEntity", EmployeeEntity.class).list();
            return fromEmployee;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public EmployeeEntity getEmployeeById(String id) {
        Session session = HibernateUtil.getSession();
        try {
            Query<EmployeeEntity> query = session.createQuery("FROM EmployeeEntity WHERE id = :employeeId", EmployeeEntity.class);
            query.setParameter("employeeId", id);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
