package com.dpworld.projectconnect.repository;

import com.dpworld.projectconnect.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User,String> {

    User findByEmail(String email);



}
