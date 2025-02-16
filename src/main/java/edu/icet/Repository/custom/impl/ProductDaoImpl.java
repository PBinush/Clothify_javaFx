package edu.icet.Repository.custom.impl;

import edu.icet.Repository.custom.ProductDao;
import edu.icet.entity.ProductEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(ProductEntity productEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(productEntity);
            transaction.commit();
            System.out.println(productEntity);
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
    public boolean update(ProductEntity productEntity) {
        return false;
    }

    @Override
    public ProductEntity search(String id) {
        return null;
    }

    @Override
    public List<ProductEntity> getAll() {
        Session session = HibernateUtil.getSession();
        try {
            List<ProductEntity> fromProducts = session.createQuery("FROM ProductEntity", ProductEntity.class).list();
            return fromProducts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
