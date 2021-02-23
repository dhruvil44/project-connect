package com.dpworld.projectconnect.repository;

import com.dpworld.projectconnect.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepo extends JpaRepository<Request,Integer> {

    //this returns the list of the requests that the current logged in user has got.
    @Query("from Request where email_whorecieved=:email")
    public List<Request> findByEmailWhoSentToThisUser(@Param("email") String email);

    //this returns the list of the requests that the current logged in user has sent to others.
    @Query("from Request where email_whosent=:email")
    public List<Request> findByEmailWhoSent(@Param("email") String email);

    //this is to get the id of a particular request
    @Query("select id from Request where email_whosent=:email1 AND email_whorecieved=:email2")
    public int getId(@Param("email1") String email1,@Param("email2")String email2);
}
