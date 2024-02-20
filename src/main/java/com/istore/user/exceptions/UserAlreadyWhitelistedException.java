package com.istore.user.exceptions;

public class UserAlreadyWhitelistedException extends RuntimeException {
    public UserAlreadyWhitelistedException(String email) {
        super("The email " + email + " is already whitelisted.");
    }
}
