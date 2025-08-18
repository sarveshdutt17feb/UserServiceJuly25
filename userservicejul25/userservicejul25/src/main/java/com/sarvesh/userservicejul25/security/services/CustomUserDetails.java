package com.sarvesh.userservicejul25.security.services;

import com.sarvesh.userservicejul25.models.Role;
import com.sarvesh.userservicejul25.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<GrantedAuthority> authorities;


    public CustomUserDetails(User user){
        this.password=user.getPassword();
        this.username=user.getEmail();
        this.accountNonExpired=true;
        this.accountNonLocked=true;
        this.credentialsNonExpired=false;
        this.enabled=true;
        this.authorities = new ArrayList<>();
        for(Role role: user.getRoles()){
            authorities.add(new CustomGrantedAuthorities(role));
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
