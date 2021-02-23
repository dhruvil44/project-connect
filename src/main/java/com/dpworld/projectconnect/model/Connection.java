package com.dpworld.projectconnect.model;

import javax.persistence.*;

@Entity
@Table(name="connection")
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="email1")
    private String email1;

    @Column(name="email2")
    private String email2;

    public Connection()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", email1='" + email1 + '\'' +
                ", email2='" + email2 + '\'' +
                '}';
    }
}
