package com.dpworld.projectconnect.model;

import javax.persistence.*;

@Entity
@Table(name="authorities")
public class Authorities {

    @Id
    @Column(name="email")
    private String email;

    @Column(name="authority")
    private String authority;

    public Authorities() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "email='" + email + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
