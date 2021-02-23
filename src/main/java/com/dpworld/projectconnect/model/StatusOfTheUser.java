package com.dpworld.projectconnect.model;


//this class is to check the status of any user with the current logged in user
//like if any user is connected with the current logged in user then isConnection field = true
//if the current logged in user has requested any user then isRequested = true
//if the current logged in user has got the request from any user then hasRequestCame=true
public class StatusOfTheUser {

    private User user;

    private boolean isConnection;

    private boolean isRequested;

    private boolean hasRequestCame;

    public StatusOfTheUser()
    {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isConnection() {
        return isConnection;
    }

    public void setConnection(boolean connection) {
        isConnection = connection;
    }

    public boolean isRequested() {
        return isRequested;
    }

    public void setRequested(boolean requested) {
        isRequested = requested;
    }

    public boolean isHasRequestCame() {
        return hasRequestCame;
    }

    public void setHasRequestCame(boolean hasRequestCame) {
        this.hasRequestCame = hasRequestCame;
    }

    @Override
    public String toString() {
        return "IsConnection{" +
                "user=" + user +
                ", isConnection=" + isConnection +
                ", isRequested=" + isRequested +
                ", hasRequestCame=" + hasRequestCame +
                '}';
    }
}
