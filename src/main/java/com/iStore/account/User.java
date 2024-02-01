package com.iStore.account;


public class User {
    public String email;
    public String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean checkLogin (String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
}
