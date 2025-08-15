package com.sarvesh.userservicejul25.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;
@Data
@Entity(name = "users")
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
    @ManyToMany
    List<Role> roles;
}
