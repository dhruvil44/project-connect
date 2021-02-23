package com.dpworld.projectconnect.repository;

import com.dpworld.projectconnect.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConnectionRepo extends JpaRepository<Connection,Integer> {

    List<Connection> findByEmail1(String email);

    @Query("select id from Connection where email1=:email1 AND email2=:email2")
    public int getId(@Param("email1") String email1,@Param("email2")String email2);

}
