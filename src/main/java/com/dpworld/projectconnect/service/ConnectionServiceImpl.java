package com.dpworld.projectconnect.service;

import com.dpworld.projectconnect.model.Connection;
import com.dpworld.projectconnect.repository.ConnectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConnectionServiceImpl implements ConnectionService {

    @Autowired
    private ConnectionRepo connectionRepo;

    @Override
    //saves a connection
    public void save(Connection connection) {
        connectionRepo.save(connection);
    }

    @Override
    //returns the list of the connections that the current logged in user has.
    public List<Connection> getConnections(String email) {

        List<Connection> connections = connectionRepo.findByEmail1(email);

        return connections;
    }

    @Override
    public int getIdOfConnection(String email1, String email2) {
        return connectionRepo.getId(email1,email2);
    }

    @Override
    public void deleteConnectionById(int id) {
            connectionRepo.deleteById(id);
    }
}
