package com.istore.user;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class UserModel implements UserLoginEventsSubscriber {

    private final List<User> usersList;
    private final List<UserLoginEventsListener> listeners;

    public UserModel() {
        this.usersList = new ArrayList<>();
        this.listeners = new ArrayList<>();
    }

    public void addUser(User user) {
        usersList.add(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return usersList.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    public boolean updateUser(User user) {
        usersList.removeIf(u -> u.getEmail().equals(user.getEmail()));
        return usersList.add(user);
    }

    public void login(User user) {
        listeners.forEach(listener -> listener.onLogin(user));
    }

    @Override
    public void subscribe(UserLoginEventsListener listener) {
        listeners.add(listener);
    }
}