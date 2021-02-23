package com.dpworld.projectconnect.service;

import com.dpworld.projectconnect.model.Request;

import java.util.List;

public interface RequestService {

    //saves the request
    public void saveRequest(Request request);

    //return the list of the requests that the logged in used has got from other users.
    public List<Request> getRequests(String email);

    //returns the id of the request
    public int getIdOfRequest(String email_whosent,String email_whorecieved);


    //deletes the request by id
    public void deleteRequestById(int id);


    //returns the list of the requests the the logged in user has sent to other users.
    public List<Request> getRequestsWhoSentTheRequest(String email);
}
