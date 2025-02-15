package edu.icet.service.custom.impl;

import edu.icet.Repository.DaoFactory;
import edu.icet.Repository.custom.UserDao;
import edu.icet.dto.User;
import edu.icet.entity.UserEntity;
import edu.icet.service.custom.UserService;
import edu.icet.util.DaoType;
import org.modelmapper.ModelMapper;

public class UserServiceImpl implements UserService {
    @Override
    public boolean saveUser(User user) {
        UserEntity entity = new ModelMapper().map(user, UserEntity.class);
        UserDao daoType = DaoFactory.getInstance().getDaoType(DaoType.USER);
        if (daoType.save(entity)){
            return true;
        }
        return false;
    }
}
