package com.wade.crys.user;

import java.util.Optional;

import org.apache.jena.query.Dataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wade.crys.user.interfaces.UserRepository;
import com.wade.crys.user.interfaces.UserService;
import com.wade.crys.user.model.User;
import com.wade.crys.user.model.UserDetailsImpl;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Dataset dataset;

    @Override
    public void addUser(User user) {

        userRepository.addUser(user);
    }

    @Override
    public Optional<User> getUserById(String uuid) {

        return userRepository.getUserById(uuid);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {

        return userRepository.getUserByEmail(email);
    }

    @Override
    public void updateUser(String id, User updatedUser) {
        Optional<User> optional = userRepository.getUserById(id);

        if(optional.isPresent()) {
            User user = optional.get();

            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setEmailNotification(updatedUser.isEmailNotification());
            user.setAlerts(updatedUser.getAlerts());
            user.setFavoriteCoins(updatedUser.getFavoriteCoins());

            //userRepository.addUser(user);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByEmail(s);

        if(!user.isPresent()) {
            throw new UsernameNotFoundException("User with email: " + s + " not found");
        }

        return new UserDetailsImpl(user.get());
    }

}
