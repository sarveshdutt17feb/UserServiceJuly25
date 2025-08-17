package com.sarvesh.userservicejul25.services;

import com.sarvesh.userservicejul25.exceptions.UnAuthorizedException;
import com.sarvesh.userservicejul25.exceptions.UserNotFoundException;
import com.sarvesh.userservicejul25.models.Token;
import com.sarvesh.userservicejul25.models.User;

public interface UserService {
    Token login(String email, String password) throws UserNotFoundException, UnAuthorizedException;
    User signUp(String name, String email, String password);
    User validateToken(String tokenValue);
    void logout(String tokenValue);
}
