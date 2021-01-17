package com.wade.crys.user.interfaces;

import java.util.Optional;

import com.wade.crys.user.model.User;

public interface UserRepository {

    Optional<User> getUserById(String uuid);

    Optional<User> getUserByEmail(String email);

	void addUser(User user);
}
