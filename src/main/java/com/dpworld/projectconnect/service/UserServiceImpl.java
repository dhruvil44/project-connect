package com.dpworld.projectconnect.service;

import com.dpworld.projectconnect.model.User;
import com.dpworld.projectconnect.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    //saves the user
    public void save(User theUser) {
        userRepo.save(theUser);
    }

    @Override
    //returns a particular user
    public User getUser(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    //returns the list of all users
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users;
    }
}
