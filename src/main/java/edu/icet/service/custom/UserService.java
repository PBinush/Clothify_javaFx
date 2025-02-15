package edu.icet.service.custom;

import edu.icet.dto.User;
import edu.icet.service.SuperService;

public interface UserService extends SuperService {
    boolean saveUser(User user);
}
