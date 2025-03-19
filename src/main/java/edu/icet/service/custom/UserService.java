package edu.icet.service.custom;

import edu.icet.dto.User;
import edu.icet.service.SuperService;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface UserService extends SuperService {
    boolean saveUser(User user);
    boolean updateUser(User user);
    List<User> getAll();
}
