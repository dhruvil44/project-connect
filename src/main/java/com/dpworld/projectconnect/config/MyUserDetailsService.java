package com.dpworld.projectconnect.config;

import com.dpworld.projectconnect.model.Authorities;
import com.dpworld.projectconnect.model.User;
import com.dpworld.projectconnect.repository.AuthoritiesRepo;
import com.dpworld.projectconnect.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthoritiesRepo authoritiesRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(s);
        Authorities authorities=authoritiesRepo.findByEmail(s);


        return new MyUserDetails(user,authorities);
    }
}
