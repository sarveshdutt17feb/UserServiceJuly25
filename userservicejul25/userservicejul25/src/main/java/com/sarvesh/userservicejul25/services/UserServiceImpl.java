package com.sarvesh.userservicejul25.services;

import com.sarvesh.userservicejul25.exceptions.UnAuthorizedException;
import com.sarvesh.userservicejul25.exceptions.UserNotFoundException;
import com.sarvesh.userservicejul25.models.Token;
import com.sarvesh.userservicejul25.models.User;
import com.sarvesh.userservicejul25.repositories.TokenRepository;
import com.sarvesh.userservicejul25.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    public UserServiceImpl(UserRepository userRepository,TokenRepository tokenRepository){
        this.userRepository= userRepository;
        this.tokenRepository=tokenRepository;
    }

    @Override
    public Token login(String email, String password) throws UserNotFoundException, UnAuthorizedException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            //redirect to signup page
            throw new UserNotFoundException("user with email "+email+"not found");
        }
        User user = optionalUser.get();
        if(user.getPassword().equals(password)){
            //login successful, create token

            Token token = new Token();
            token.setUser(user);
            token.setValue("absbvdhccsvnvcvhvdvdvhvdhvdhvdgh");

            Date currentDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);

            //add 30 days to the calendar
            calendar.add(Calendar.DAY_OF_MONTH,30);

            //get the updated time as a Date object
            Date dateAfter30Days = calendar.getTime();



            token.setExpiryAt(dateAfter30Days);
            return tokenRepository.save(token);

        }
        //login failed
        throw new UnAuthorizedException("login failed");
    }

    @Override
    public User signUp(String name, String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            //redirect to login
            return optionalUser.get();
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        //TODO - we should store the password in encoded format using Bcrypt Password Encoder
        user.setPassword(password);


        return userRepository.save(user);
    }

    @Override
    public User validateToken(String tokenValue) {
        //check if the token is present in db, token is not deleted
        //token's expiry time is greater than the current time.
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(
                tokenValue,
                false,
                new Date());
        if(optionalToken.isEmpty()){
            //token is not present in db
            //token invalid
            return null;
        }
        return optionalToken.get().getUser();
    }

    @Override
    public void logout(String tokenValue) {
        Optional<Token> optionalToken = tokenRepository.findByValue(tokenValue);
        if(optionalToken.isEmpty()){
           throw new RuntimeException("token invalid");
        }
        Token token = optionalToken.get();
        token.setDeleted(true);
        tokenRepository.save(token);
    }
}
