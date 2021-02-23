package com.dpworld.projectconnect.service;

import com.dpworld.projectconnect.model.Request;
import com.dpworld.projectconnect.repository.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepo requestRepo;

    @Override
    //saves the request
    public void saveRequest(Request request) {
        requestRepo.save(request);
    }

    @Override
    //returns the list of the requests that the logged in user has got from other users.
    public List<Request> getRequests(String email) {
        return requestRepo.findByEmailWhoSentToThisUser(email);
    }

    @Override
    //returns the id of a particular request
    public int getIdOfRequest(String email_whosent, String email_whorecieved) {
        return requestRepo.getId(email_whosent,email_whorecieved);
    }

    @Override
    //deletes a request by id
    public void deleteRequestById(int id) {

        requestRepo.deleteById(id);
    }

    @Override
    //returns the list of the requests that the logged in user has sent to other users.
    public List<Request> getRequestsWhoSentTheRequest(String email) {
        return requestRepo.findByEmailWhoSent(email);
    }
}
