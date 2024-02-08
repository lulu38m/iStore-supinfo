package com.iStore.account;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    public String email;
    public String passwordHash;
    public  Role role;
    public String id;
    public String pseudo;



    public User(String email, String password, String role, String id, String pseudo) {
        this.email = email;
        this.passwordHash = password;
        this.role = Role.valueOf(role);
        this.id = id;
        this.pseudo = pseudo;
    }

    public boolean checkLogin(String email, String password) {
        return this.email.equals(email) && BCrypt.checkpw(password, this.passwordHash);
    }
}
