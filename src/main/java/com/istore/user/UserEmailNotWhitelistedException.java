package com.istore.user;

public class UserEmailNotWhitelistedException extends Exception {

    public UserEmailNotWhitelistedException(String email) {
        super("The email " + email + " is not whitelisted.");
    }

}
