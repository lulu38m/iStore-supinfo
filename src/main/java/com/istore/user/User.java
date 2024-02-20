package com.istore.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class User {
    private final UUID id;
    private String email;
    private String pseudo;
    private Role role;
    private String passwordHash;

    public User(UUID id, String email, String pseudo, Role role) {
        this.id = id;
        this.email = email;
        this.pseudo = pseudo;
        this.role = role;
    }

}
