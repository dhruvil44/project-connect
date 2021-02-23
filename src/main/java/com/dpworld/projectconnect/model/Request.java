package com.dpworld.projectconnect.model;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name="request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="email_whosent")
    private String email_whosent;

    @Column(name="email_whorecieved")
    private String email_whorecieved;

    public Request()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail_whosent() {
        return email_whosent;
    }

    public void setEmail_whosent(String email_whosent) {
        this.email_whosent = email_whosent;
    }

    public String getEmail_whorecieved() {
        return email_whorecieved;
    }

    public void setEmail_whorecieved(String email_whorecieved) {
        this.email_whorecieved = email_whorecieved;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", email_whosent='" + email_whosent + '\'' +
                ", email_whorecieved='" + email_whorecieved + '\'' +
                '}';
    }
}
