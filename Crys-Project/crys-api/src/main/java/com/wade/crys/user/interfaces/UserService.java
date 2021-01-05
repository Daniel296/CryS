package com.wade.crys.user.interfaces;

import com.wade.crys.user.model.User;

import java.util.Optional;

public interface UserService {

    void addUser(User user);

    Optional<User> getUserById(String uuid);

    void updateUser(String id, User user);

}
