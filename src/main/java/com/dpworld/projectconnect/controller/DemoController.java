package com.dpworld.projectconnect.controller;

import com.dpworld.projectconnect.config.MyUserDetails;
import com.dpworld.projectconnect.model.Authorities;
import com.dpworld.projectconnect.model.User;
import com.dpworld.projectconnect.repository.AuthoritiesRepo;
import com.dpworld.projectconnect.repository.UserRepo;
import com.dpworld.projectconnect.service.AuthoritiesService;
import com.dpworld.projectconnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DemoController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthoritiesService authoritiesService;

    @GetMapping("/")
    public String home()
    {
        return "home";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model)
    {
        User user = new User();

        model.addAttribute("user",user);

        return "register-form";
    }

    @PostMapping("/processForm")
    public String processForm(@ModelAttribute("user") User theUser)
    {
        User user = userService.getUser(theUser.getEmail());

        if(user!=null)
        {
            return "useralreadyregistered";
        }

        if(user==null) {
            theUser.setActive(true);

            userService.save(theUser);

            Authorities authorities = new Authorities();
            authorities.setEmail(theUser.getEmail());
            authorities.setAuthority("ROLE_USER");
            authoritiesService.save(authorities);
        }

        return "home";
    }

    @GetMapping("/editInfo")
    public String editInfo(@AuthenticationPrincipal MyUserDetails userDetails, Model theModel)
    {
        String email=userDetails.getUsername();
        String password = userDetails.getPassword();
        String handleName=userDetails.getHandleUserName();

        User user = new User();

        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(handleName);

        theModel.addAttribute("user",user);

        return "edit-info";
    }

    @PostMapping("/processEditInfo")
    public String processEditInfo(@ModelAttribute("user")User theUser,@AuthenticationPrincipal MyUserDetails userDetails)
    {
            theUser.setActive(true);
            userService.save(theUser);
            userDetails.setHandleUserName(theUser.getUsername());
            userDetails.setPassword(theUser.getPassword());
            userDetails.setUsername(theUser.getEmail());

            Authorities authorities=authoritiesService.get(theUser.getEmail());

            if(authorities.getAuthority().equals("ROLE_ADMIN"))
            {
                return "redirect:/admin/home";
            }

            return "redirect:/user/home";
    }

}
