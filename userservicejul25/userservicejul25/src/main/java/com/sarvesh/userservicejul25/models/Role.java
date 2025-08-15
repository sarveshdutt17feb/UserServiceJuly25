package com.sarvesh.userservicejul25.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data

@Entity(name = "roles")
public class Role extends BaseModel{
    private String value;
}
