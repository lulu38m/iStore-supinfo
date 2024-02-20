package com.istore.user.exceptions;

import com.istore.user.User;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(User user) {
        super("User with email " + user.getEmail() + " already exists");
    }
}
