package com.istore.user;

import lombok.Getter;

@Getter
public class User {
    private final String email;
    private final String username;
    private final String passwordHash;
    private final Role role;
    private String id;

    public User(String email, String username, String passwordHash, Role role) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.username = username;
    }
}
