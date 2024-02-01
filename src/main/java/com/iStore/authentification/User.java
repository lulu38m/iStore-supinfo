package com.iStore.authentification;


public class User {
    public String email;
    public String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }

    public boolean checkLogin (String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
}
