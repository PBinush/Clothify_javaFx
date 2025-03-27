package edu.icet.Repository.custom.impl;

import edu.icet.Repository.custom.OrderDao;
import edu.icet.db.DBConnection;
import edu.icet.dto.Order;
import edu.icet.entity.OrderEntity;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity orderEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(orderEntity);
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
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            OrderEntity orderEntity = session.get(OrderEntity.class, id);
            session.delete(orderEntity);
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
    public boolean update(OrderEntity orderEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(orderEntity);
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
    public OrderEntity search(String id) {
        return null;
    }

    @Override
    public String getLastId() {
        Connection connection = DBConnection.getInstance().getConnection();
        String lastId = null;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
            if (resultSet.next()) {
                lastId = resultSet.getString("orderId");
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastId;
    }

    @Override
    public List<OrderEntity> getAll() {
        Session session = HibernateUtil.getSession();
        try {
            List<OrderEntity> fromOrder = session.createQuery("FROM OrderEntity", OrderEntity.class).list();
            return fromOrder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public OrderEntity getOrderById(String id) {
        Session session = HibernateUtil.getSession();
        try {
            Query<OrderEntity> query = session.createQuery("FROM OrderEntity WHERE id = :orderId", OrderEntity.class);
            query.setParameter("orderId", id);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<OrderEntity> getOrdersByEmployeeId(String id) {
        Session session = HibernateUtil.getSession();
        try {
            Query<OrderEntity> query = session.createQuery("FROM OrderEntity WHERE employeeId = :employeeId", OrderEntity.class);
            query.setParameter("employeeId", id);
            return query.getResultList(); // Return list of orders
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return empty list on failure
        } finally {
            session.close();
        }
    }
}

