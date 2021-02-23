package com.dpworld.projectconnect.service;

import com.dpworld.projectconnect.model.Authorities;

public interface AuthoritiesService {

    //saves the authority
    public void save(Authorities authorities);

    //returns the authorities of the logged in user.
    public Authorities get(String email);
}
