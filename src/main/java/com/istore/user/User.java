package com.istore.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private UUID id;
    private final String email;
    private final String pseudo;
    private String passwordHash;
    private final Role role;

    public User(UUID id, String email, String pseudo, Role role) {
        this.id = id;
        this.email = email;
        this.pseudo = pseudo;
        this.role = role;
    }

}
