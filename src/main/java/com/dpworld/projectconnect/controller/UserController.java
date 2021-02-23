package com.dpworld.projectconnect.controller;

import com.dpworld.projectconnect.config.MyUserDetails;
import com.dpworld.projectconnect.model.Connection;
import com.dpworld.projectconnect.model.StatusOfTheUser;
import com.dpworld.projectconnect.model.Request;
import com.dpworld.projectconnect.model.User;
import com.dpworld.projectconnect.service.ConnectionService;
import com.dpworld.projectconnect.service.RequestService;
import com.dpworld.projectconnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    //returns the home page
    @GetMapping("/home")
    public String user()
    {
        return "user";
    }

    //returns the account details section
    @GetMapping("/accountdetails")
    public String accountDetails(@AuthenticationPrincipal MyUserDetails userDetails, Model theModel)
    {
        //getting the email of the logged in user
        String email = userDetails.getUsername();

        //getting the password of the logged in user
        String password=userDetails.getPassword();

        //getting the handlename(username) of the logged in user
        String handleName=userDetails.getHandleUserName();

        theModel.addAttribute("email",email);
        theModel.addAttribute("password",password);
        theModel.addAttribute("handleName",handleName);

        return "account-details";
    }


    //returns the connections of the current logged in user
    @GetMapping("/getConnections")
    public String getConnections(@AuthenticationPrincipal MyUserDetails userDetails,Model theModel)
    {
        //getting the email of the logged in user
        String email = userDetails.getUsername();

        //getting the list of the connections of the logged in user
        List<Connection> connections = connectionService.getConnections(email);

        //this list of User type is because we want to convert all the connections which we got as type Connection as type USER
        List<User> connectionList = new ArrayList<User>();

        //getting all the connections and converting them into type USER for convinience
        for(Connection connection:connections)
        {
            connectionList.add(userService.getUser(connection.getEmail2()));
        }

        theModel.addAttribute("connectionList",connectionList);
        return "connections";
    }


    //this returns the list of all users except the current logged in user
    //it also shows the status with all the users (CONNECTED, CONNECT, REQUEST ARRIVED)
    @GetMapping("/showListOfUsers")
    public String showList(@AuthenticationPrincipal MyUserDetails userDetails,Model theModel)
    {
        //this returns the list of all users in the database
        List<User> allUsers = userService.getAllUsers();
        List<User> users = new ArrayList<User>();

        //this is the list of finalUsers which we want to send in the model without the currently logged in user
        List<StatusOfTheUser> finalUsers = new ArrayList<StatusOfTheUser>();

        //getting all the connections of the current logged in user
        List<Connection> connections = connectionService.getConnections(userDetails.getUsername());
        List<User> connectionList = new ArrayList<User>();

        //getting all the requests this currently logged in user has sent to any other user
        List<Request> requestsThisUserSent = requestService.getRequestsWhoSentTheRequest(userDetails.getUsername());
        List<User> requestListThisUserSent = new ArrayList<User>();


        //getting all the requests that this currently logged in user has got from other users
        List<Request> requestsThisUserHasGot = requestService.getRequests(userDetails.getUsername());
        List<User> requestListThisUserHasGot = new ArrayList<User>();


        //converting the CONNECTION type to the USER type
        for(Connection connection:connections)
        {
            connectionList.add(userService.getUser(connection.getEmail2()));
        }

        //converting the REQUEST type to the USER type
        for(Request request:requestsThisUserSent)
        {
            requestListThisUserSent.add(userService.getUser(request.getEmail_whorecieved()));
        }

        //converting the REQUEST type to the USER type
        for(Request request:requestsThisUserHasGot)
        {
            requestListThisUserHasGot.add(userService.getUser(request.getEmail_whosent()));
        }

        //first of all setting connection=true for all the connections of the logged in user
        for(User user:connectionList)
        {
            StatusOfTheUser statusOfTheUser = new StatusOfTheUser();
            statusOfTheUser.setUser(user);
            statusOfTheUser.setConnection(true);
            finalUsers.add(statusOfTheUser);
        }


        //setting requested=true for all the users whom this currently logged in user has sent the request
        for(User user:requestListThisUserSent)
        {
            StatusOfTheUser statusOfTheUser = new StatusOfTheUser();
            statusOfTheUser.setUser(user);
            statusOfTheUser.setConnection(false);
            statusOfTheUser.setRequested(true);
            finalUsers.add(statusOfTheUser);
        }

        //setting hasRequestCame=true for the logged in user who has got request from other users
        for(User user:requestListThisUserHasGot)
        {
            StatusOfTheUser statusOfTheUser = new StatusOfTheUser();
            statusOfTheUser.setUser(user);
            statusOfTheUser.setConnection(false);
            statusOfTheUser.setRequested(false);
            statusOfTheUser.setHasRequestCame(true);
            finalUsers.add(statusOfTheUser);
        }



        for(User user:allUsers)
        {
            //this is because the same logged in user should not see his name in the list
            if(user.getEmail().equals(userDetails.getUsername()))
            {
                continue;
            }

            int f=0;
            for(StatusOfTheUser isConnection:finalUsers)
            {
                if(isConnection.getUser().equals(user))
                {
                    f=1;
                    break;
                }
            }

            if(f==0)
            {
                StatusOfTheUser statusOfTheUser = new StatusOfTheUser();
                statusOfTheUser.setUser(user);
                statusOfTheUser.setConnection(false);
                finalUsers.add(statusOfTheUser);
            }

        }

        theModel.addAttribute("users",finalUsers);
        return "list-of-users";
    }


    //when the current logged in user sends a REQUEST to CONNECT to other user in the list
    @GetMapping("/request/{email}")
    public String request(@AuthenticationPrincipal MyUserDetails userDetails, @PathVariable String email)
    {
        //setting the email_whosent as the current logged in user
        String email_whosent = userDetails.getUsername();
        //setting the email_whorecived as the email which is passed as path variable
        String email_whorecieved = email;

        //making a new request
        Request request = new Request();
        request.setEmail_whosent(email_whosent);
        request.setEmail_whorecieved(email_whorecieved);
        //saving the request
        requestService.saveRequest(request);

        return "redirect:/user/showListOfUsers";
    }

    //shows the requests that the current logged in user has got from other users
    @GetMapping("/showRequests")
    public String showRequests(@AuthenticationPrincipal MyUserDetails userDetails,Model theModel)
    {
        //returns the list of requests that the current logged in user has got
        List<Request> requests = requestService.getRequests(userDetails.getUsername());
        theModel.addAttribute("requests",requests);

        return "list-of-requests";
    }

    //when the request is accepted
    @GetMapping("/acceptRequest/{email}")
    public String acceptRequest(@AuthenticationPrincipal MyUserDetails userDetails,@PathVariable String email)
    {
        //making a new connection
        Connection connection1 = new Connection();
        connection1.setEmail1(userDetails.getUsername());
        connection1.setEmail2(email);

        Connection connection2 = new Connection();
        connection2.setEmail1(email);
        connection2.setEmail2(userDetails.getUsername());

        //saving a new connection in the database
        connectionService.save(connection1);
        connectionService.save(connection2);

        //getting the id of the request that was sent
        int id = requestService.getIdOfRequest(email,userDetails.getUsername());

        //deleting the request from the database
        requestService.deleteRequestById(id);


        return "redirect:/user/showRequests";
    }

    //when the request is declined
    @GetMapping("/declineRequest/{email}")
    public String declineRequest(@AuthenticationPrincipal MyUserDetails userDetails,@PathVariable String email)
    {
        //getting the id of the request
        int id = requestService.getIdOfRequest(email,userDetails.getUsername());

        //deleting the request from the database
        requestService.deleteRequestById(id);

        return "redirect:/user/showRequests";
    }

    @GetMapping("/deleteConnection/{email}")
    public String deleteConnection(@AuthenticationPrincipal MyUserDetails userDetails,@PathVariable String email)
    {
        String email1 = userDetails.getUsername();
        String email2 = email;

        int id1 = connectionService.getIdOfConnection(email1,email2);
        int id2 = connectionService.getIdOfConnection(email2,email1);

        connectionService.deleteConnectionById(id1);
        connectionService.deleteConnectionById(id2);

        return "redirect:/user/getConnections";
    }


}
