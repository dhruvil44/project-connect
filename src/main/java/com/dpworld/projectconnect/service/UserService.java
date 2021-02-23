package com.dpworld.projectconnect.service;

import com.dpworld.projectconnect.model.User;

import java.util.List;

public interface UserService {


    //saves the user
    public void save(User theUser);

    //returns a particular user
    public User getUser(String email);

    //returns the list of all users
    public List<User> getAllUsers();

}
