package com.istore.user.exceptions;

public class UserEmailNotWhitelistedException extends RuntimeException {

    public UserEmailNotWhitelistedException(String email) {
        super("The email " + email + " is not whitelisted.");
    }

}
