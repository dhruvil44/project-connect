package com.dpworld.projectconnect.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="email")
    private String email;


    @Column(name="password")
    private String password;

    @Column(name="active")
    private boolean active;

    @Column(name="username")
    private String username;


//    @OneToMany(mappedBy = "email1",cascade = CascadeType.ALL)
//    private List<String> email1;
//
//
//    @OneToMany(mappedBy = "email2",cascade = CascadeType.ALL)
//    private List<String> email2;


    public User() {

    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active='" + active + '\''+
                ", username='"+username+'\''+
                '}';
    }
}
