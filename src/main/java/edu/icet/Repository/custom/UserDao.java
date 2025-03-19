package edu.icet.Repository.custom;

import edu.icet.Repository.CrudDao;
import edu.icet.entity.UserEntity;

import java.util.List;

public interface UserDao extends CrudDao<UserEntity> {
    List<UserEntity> getAll();
}
