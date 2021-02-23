package com.dpworld.projectconnect.service;

import com.dpworld.projectconnect.model.Connection;

import java.util.List;

public interface ConnectionService {

    //saves a connection
    public void save(Connection connection);

    //returns the list of the connections the current logged in user has.
    public List<Connection> getConnections(String email);

    //returns the id of the connection
    public int getIdOfConnection(String email1,String email2);

    //deletes the connection based on the id
    public void deleteConnectionById(int id);
}
