package com.sarvesh.userservicejul25.controllers;

import com.sarvesh.userservicejul25.dtos.*;
import com.sarvesh.userservicejul25.exceptions.UnAuthorizedException;
import com.sarvesh.userservicejul25.exceptions.UserNotFoundException;
import com.sarvesh.userservicejul25.models.Token;
import com.sarvesh.userservicejul25.models.User;
import com.sarvesh.userservicejul25.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody SignupRequestDto requestDto){
    User user = userService.signUp(
            requestDto.getName(),
            requestDto.getEmail(),
            requestDto.getPassword()
    );
    //convert this user object into UserDto object
    return UserDto.from(user);
    }
    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto requestDto) throws UserNotFoundException, UnAuthorizedException {
        Token token = userService.login(
                requestDto.getEmail(),
                requestDto.getPassword()
        );
        return token;
    }
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto requestDto){
        userService.logout(requestDto.getTokenValue());
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }
    //localhost:8080/users/validate/token
    @GetMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable("token") String token ){
        User user = userService.validateToken(token);

        return UserDto.from(user);
    }
}
