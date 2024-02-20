package com.istore.user.exceptions;

import com.istore.user.User;

public class UserNotAdminException extends RuntimeException {

    public UserNotAdminException(User operator) {
        super("User " + operator.getEmail() + " is not an admin");
    }

}
