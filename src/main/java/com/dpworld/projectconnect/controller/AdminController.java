package com.dpworld.projectconnect.controller;


import com.dpworld.projectconnect.config.MyUserDetails;
import com.dpworld.projectconnect.model.Connection;
import com.dpworld.projectconnect.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ConnectionService connectionService;

    @GetMapping("/home")
    public String admin()
    {
        return "admin";
    }

    @GetMapping("/accountdetails")
    public String accountDetails(@AuthenticationPrincipal MyUserDetails userDetails, Model theModel)
    {
        String email = userDetails.getUsername();
        String password=userDetails.getPassword();
        String handleName=userDetails.getHandleUserName();

        theModel.addAttribute("email",email);
        theModel.addAttribute("password",password);
        theModel.addAttribute("handleName",handleName);

        return "account-details";
    }

    @GetMapping("/getConnections")
    public String getConnections(@AuthenticationPrincipal MyUserDetails userDetails,Model theModel)
    {
        String email = userDetails.getUsername();
        List<Connection> connectionList = connectionService.getConnections(email);

        theModel.addAttribute("connectionList",connectionList);

        return "connections";
    }

}
