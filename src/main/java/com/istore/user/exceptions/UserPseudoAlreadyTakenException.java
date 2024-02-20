package com.istore.user.exceptions;

public class UserPseudoAlreadyTakenException extends RuntimeException {
    public UserPseudoAlreadyTakenException(String pseudo) {
        super("Pseudo " + pseudo + " is already taken");
    }
}
