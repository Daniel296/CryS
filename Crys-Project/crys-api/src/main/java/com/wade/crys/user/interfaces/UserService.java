package com.wade.crys.user.interfaces;

import java.util.Optional;

import com.wade.crys.user.model.User;

public interface UserService {

    void addUser(User user);

    Optional<User> getUserById(String uuid);

    Optional<User> getUserByEmail(String email);

    void updateUser(String id, User user);

}
