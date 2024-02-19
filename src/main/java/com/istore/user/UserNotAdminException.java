package com.istore.user;

public class UserNotAdminException extends Exception {

    public UserNotAdminException(User operator) {
        super("User " + operator.getEmail() + " is not an admin");
    }

}
