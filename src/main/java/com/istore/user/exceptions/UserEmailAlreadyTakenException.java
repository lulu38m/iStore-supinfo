package com.istore.user.exceptions;

public class UserEmailAlreadyTakenException extends RuntimeException {

    public UserEmailAlreadyTakenException(String email) {
        super("The email " + email + " is already taken.");
    }

}
