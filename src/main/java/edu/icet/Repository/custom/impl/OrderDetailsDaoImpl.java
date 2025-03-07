package edu.icet.Repository.custom.impl;

import edu.icet.Repository.custom.OrderDetailsDao;
import edu.icet.entity.OrderDetailsEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    @Override
    public boolean save(OrderDetailsEntity orderDetailsEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(orderDetailsEntity);
            transaction.commit();
            System.out.println(orderDetailsEntity);
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
    public boolean update(OrderDetailsEntity orderDetailsEntity) {
        return false;
    }

    @Override
    public OrderDetailsEntity search(String id) {
        return null;
    }

    @Override
    public String getLastId() {
        return "";
    }

    @Override
    public List<OrderDetailsEntity> getAll() {
        Session session = HibernateUtil.getSession();
        try {
            List<OrderDetailsEntity> fromOrderDetails = session.createQuery("FROM OrderDetailsEntity", OrderDetailsEntity.class).list();
            return fromOrderDetails;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
