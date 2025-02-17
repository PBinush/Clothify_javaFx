package edu.icet.Repository.custom.impl;

import edu.icet.Repository.custom.UserDao;
import edu.icet.entity.UserEntity;
import edu.icet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        return false;
    }

    @Override
    public boolean update(UserEntity user) {
        return false;
    }

    @Override
    public UserEntity search(String id) {
        return null;
    }

    @Override
    public String getLastId() {
        return "";
    }
}
