package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.UserDao;
import edu.icet.dto.User;
import edu.icet.entity.UserEntity;
import edu.icet.service.custom.UserService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    final UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);

    @Override
    public boolean saveUser(User user) {
        UserEntity map = new ModelMapper().map(user, UserEntity.class);
        if (userDao.save(map)){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        UserEntity map = new ModelMapper().map(user, UserEntity.class);
        if (userDao.update(map)){
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        List<User> allUser = new ArrayList<>();
        userDao.getAll().forEach(userEntity -> {
                allUser.add(new ModelMapper().map(userEntity,User.class)) ;
        });
        return allUser;
    }
}
