package com.iStore.account;

import org.mindrot.jbcrypt.BCrypt;


public class User {
    public String email;
    public String passwordHash;

    public User(String email, String password) {
        this.email = email;
        this.passwordHash = password;
    }

    public boolean checkLogin(String email, String password) {
        return this.email.equals(email) && BCrypt.checkpw(password, this.passwordHash);
    }
}
