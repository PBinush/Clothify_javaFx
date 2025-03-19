package edu.icet.Repository.custom.impl;

import edu.icet.Repository.custom.UserDao;
import edu.icet.entity.UserEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(UserEntity userEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(userEntity);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            UserEntity userEntity = session.get(UserEntity.class, id);
            session.delete(userEntity);
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
    public boolean update(UserEntity user) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
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
    public UserEntity search(String id) {
        return null;
    }

    @Override
    public String getLastId() {
        return " ";
    }

    @Override
    public List<UserEntity> getAll() {
        Session session = HibernateUtil.getSession();
        try {
            List<UserEntity> fromUser = session.createQuery("FROM UserEntity", UserEntity.class).list();
            return fromUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
