package com.sarvesh.userservicejul25.dtos;

import com.sarvesh.userservicejul25.models.Role;
import com.sarvesh.userservicejul25.models.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class UserDto {
    private String email;
    private String name;
    private List<String> roles;

    public static UserDto from(User user){
        if(user==null) return null;
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setRoles(new ArrayList<>());
        for(Role role: user.getRoles()){
            userDto.getRoles().add(role.getValue());
        }

        return userDto;
    }
}
