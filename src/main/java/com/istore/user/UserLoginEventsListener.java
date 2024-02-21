package com.istore.user;

public interface UserLoginEventsListener {
    void onLogin(User user);

    void onLogout();

    void onUpdate(User newUser);

    void onDelete(User user);
}
