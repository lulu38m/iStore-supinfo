package com.iStore.account;

import java.util.List;
import java.util.Optional;

public class UserManagementSystem {
    private final List<User> userList;

    public UserManagementSystem(List<User> userList) {
        this.userList = userList;
    }

    public Optional<User> readUser(String email) {
        return userList.stream().filter(user -> user.email.equals(email)).findFirst();
    }

    public boolean updateUser(String email, String newPassword, String newRole, String newPseudo) {
        Optional<User> optionalUser = readUser(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.passwordHash = newPassword;
            user.role = Role.valueOf(newRole);
            user.pseudo = newPseudo;
            return true;
        }

        return false;
    }

    public boolean deleteUser(String email) {
        Optional<User> optionalUser = readUser(email);

        if (optionalUser.isPresent()) {
            userList.remove(optionalUser.get());
            return true;
        }

        return false;
    }
}