package com.istore.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {
    private final String email;
    private final String pseudo;
    private final String passwordHash;
    private final Role role;
    private String id;
}
