package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.UserDao;
import edu.icet.dto.User;
import edu.icet.entity.UserEntity;
import edu.icet.service.custom.UserService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

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
}
