package edu.icet.Repository.custom.impl;

import edu.icet.Repository.custom.SupplierDao;
import edu.icet.db.DBConnection;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            SupplierEntity supplierEntity = session.get(SupplierEntity.class, id);
            session.delete(supplierEntity);
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
    public boolean update(SupplierEntity supplierEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(supplierEntity);
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
    public SupplierEntity search(String id) {
        return null;
    }

    @Override
    public String getLastId() {
        Connection connection = DBConnection.getInstance().getConnection();
        String lastId = null;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT id FROM supplier ORDER BY id DESC LIMIT 1");
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
    public List<SupplierEntity> getAll() {
        Session session = HibernateUtil.getSession();
        try {
            List<SupplierEntity> fromSupplier = session.createQuery("FROM SupplierEntity", SupplierEntity.class).list();
            return fromSupplier;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public SupplierEntity getSupplierById(String id) {
        Session session = HibernateUtil.getSession();
        try {
            Query<SupplierEntity> query = session.createQuery("FROM SupplierEntity WHERE id = :supplierId", SupplierEntity.class);
            query.setParameter("supplierId", id);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
