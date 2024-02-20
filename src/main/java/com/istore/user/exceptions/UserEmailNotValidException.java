package com.istore.user.exceptions;

public class UserEmailNotValidException extends RuntimeException {

    public UserEmailNotValidException(String email) {
        super("The email " + email + " is not valid");
    }

}
