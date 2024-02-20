package com.istore.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private final UUID id;
    private String email;
    private String pseudo;
    private final String passwordHash;
    private Role role;
}
