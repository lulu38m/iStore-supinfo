package com.istore.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class User {
    private final String email;
    private final String pseudo;
    private final String passwordHash;
    private final Role role;
    private UUID id;
}
