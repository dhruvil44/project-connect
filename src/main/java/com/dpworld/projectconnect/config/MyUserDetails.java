package com.dpworld.projectconnect.config;

import com.dpworld.projectconnect.model.Authorities;
import com.dpworld.projectconnect.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private String email;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorityList;
    private String handleUserName;

    public MyUserDetails(User user, Authorities authorities)
    {
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.active=user.isActive();
        this.authorityList= Arrays.stream(authorities.getAuthority().split(","))
                            .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        this.handleUserName=user.getUsername();
    }

    public String getHandleUserName()
    {
        return this.handleUserName;
    }
    public void setHandleUserName(String handleUserName)
    {
         this.handleUserName=handleUserName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password=password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setUsername(String username)
    {
        this.email=username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
