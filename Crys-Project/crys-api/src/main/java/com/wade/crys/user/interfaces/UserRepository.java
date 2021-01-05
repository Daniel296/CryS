package com.wade.crys.user.interfaces;

import com.wade.crys.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> getUserById(String uuid);

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers();

    void addUser(User user);

}
