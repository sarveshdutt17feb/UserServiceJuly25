package com.sarvesh.userservicejul25.security.services;

import com.sarvesh.userservicejul25.exceptions.UserNotFoundException;
import com.sarvesh.userservicejul25.models.User;
import com.sarvesh.userservicejul25.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("user with username "+username+" not found");
        }
        User user = optionalUser.get();

        return new CustomUserDetails(user);
    }
}
