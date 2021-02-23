package com.dpworld.projectconnect.service;

import com.dpworld.projectconnect.model.Authorities;
import com.dpworld.projectconnect.repository.AuthoritiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

    @Autowired
    private AuthoritiesRepo authoritiesRepo;


    @Override
    //saves the authorities of the user.
    public void save(Authorities authorities) {
        authoritiesRepo.save(authorities);
    }

    @Override
    //returns the authorities of the current logged in user.
    public Authorities get(String email) {
        return authoritiesRepo.findByEmail(email);
    }
}
