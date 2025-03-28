package edu.icet.Repository.custom.impl;

import edu.icet.Repository.custom.ProductDao;
import edu.icet.db.DBConnection;
import edu.icet.entity.OrderDetailsEntity;
import edu.icet.entity.ProductEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            ProductEntity productEntity = session.get(ProductEntity.class, id);
            session.delete(productEntity);
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
    public boolean update(ProductEntity productEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(productEntity);
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
    public ProductEntity search(String id) {
        return null;
    }

    @Override
    public String getLastId() {
        Connection connection = DBConnection.getInstance().getConnection();
        String lastId = null;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT id FROM products ORDER BY id DESC LIMIT 1");
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
    public List<ProductEntity> getAllGentsProduct() {
        Session session = HibernateUtil.getSession();
        try {
            List<ProductEntity> gentsProducts = session.createQuery(
                            "FROM ProductEntity p WHERE p.category = :category", ProductEntity.class)
                    .setParameter("category", "Gents")
                    .list();
            return gentsProducts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<ProductEntity> getAllKidsProduct() {
        Session session = HibernateUtil.getSession();
        try {
            List<ProductEntity> gentsProducts = session.createQuery(
                            "FROM ProductEntity p WHERE p.category = :category", ProductEntity.class)
                    .setParameter("category", "Kids")
                    .list();
            return gentsProducts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<ProductEntity> getAllLadiesProduct() {
        Session session = HibernateUtil.getSession();
        try {
            List<ProductEntity> gentsProducts = session.createQuery(
                            "FROM ProductEntity p WHERE p.category = :category", ProductEntity.class)
                    .setParameter("category", "Ladies")
                    .list();
            return gentsProducts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public ProductEntity getProductById(String id) {
        Session session = HibernateUtil.getSession();
        try {
            Query<ProductEntity> query = session.createQuery("FROM ProductEntity WHERE id = :productId", ProductEntity.class);
            query.setParameter("productId", id);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
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

    @Override
    public List<String> getAllProductIds() {
        Session session = HibernateUtil.getSession();
        try {
            Query<String> query = session.createQuery("SELECT p.id FROM ProductEntity p", String.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public ProductEntity getProductByName(String name) {
        Session session = HibernateUtil.getSession();
        try {
            Query<ProductEntity> query = session.createQuery("FROM ProductEntity WHERE name = :productName", ProductEntity.class);
            query.setParameter("productName", name);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(List<ProductEntity> productEntityList) {
        productEntityList.forEach(productEntity -> {
            update(productEntity);
        });
        return !productEntityList.isEmpty();
    }


}
