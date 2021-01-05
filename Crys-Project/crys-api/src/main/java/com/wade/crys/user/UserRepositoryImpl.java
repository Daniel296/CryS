package com.wade.crys.user;

import com.wade.crys.user.model.User;
import com.wade.crys.user.interfaces.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("uuid-13ew2-21312-31sq", "Daniel", "Oana", "test", "test", "07656784385", true, new ArrayList<>(), new ArrayList<>()));
        users.add(new User("uuid-oa423-rwe3-423wr", "Rares", "Podaru", "rares.podaru@gmail.com", "password-21", "07656784385", true, new ArrayList<>(), new ArrayList<>()));
    }

    @Override
    public Optional<User> getUserById(String uuid) {
        return users.stream()
                .filter(user -> user.getUuid().equals(uuid))
                .findFirst();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
