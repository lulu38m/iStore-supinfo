package com.iStore.account;

import java.util.List;

public class CreateAccSystem {
    //systeme to create account and add to the list
    private List<User> userList;

    public CreateAccSystem(List<User> userList) {
        this.userList = userList;
    }

    public boolean createUser(String email, String password) {
        if (email == null || password == null) {
            return false;
        }

        for (User user : userList) {
            if (user.email.equals(email)) {
                return false;
            }
        }

        User user = new User(email, password, "user", String.valueOf(userList.size() + 1), email.split("@")[0]);
        userList.add(user);
        return true;
    }
}
