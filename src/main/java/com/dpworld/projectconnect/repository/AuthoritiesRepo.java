package com.dpworld.projectconnect.repository;

import com.dpworld.projectconnect.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepo extends JpaRepository<Authorities,String> {

    Authorities findByEmail(String email);

}
