package com.sarvesh.userservicejul25.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
@Data
@Entity(name = "tokens")
public class Token extends BaseModel{
    private String value;

    @ManyToOne
    private User user;
    private Date expiryDate;
}
