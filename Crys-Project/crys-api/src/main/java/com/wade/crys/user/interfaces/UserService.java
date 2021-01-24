package com.wade.crys.user.interfaces;

import java.util.List;
import java.util.Optional;

import com.wade.crys.user.model.User;

public interface UserService {

    List<User> getUsersWithEmailNotification();

    Optional<User> getUserById(String uuid);

    Optional<User> getUserByEmail(String email);

    void addUser(User user);

    void updateUser(String id, User user);

}
