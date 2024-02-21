package com.istore.menu;

import com.istore.MainWindow;
import com.istore.WindowManager;
import com.istore.user.ListUsersWindow;
import com.istore.user.User;
import com.istore.user.UserController;
import com.istore.user.UserProfileWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UserMenu extends JMenu {

    private final UserController userController;
    private final WindowManager windowManager;
    private final User loggedInUser;
    private final MainWindow mainWindow;

    public UserMenu(UserController userController, User loggedInUser, WindowManager windowManager, MainWindow mainWindow) {
        super("User");
        this.userController = userController;
        this.loggedInUser = loggedInUser;
        this.windowManager = windowManager;
        this.mainWindow = mainWindow;

        add(new UserProfileButton());
        add(new UserMenuListUsersButton());
        add(new UserMenuLogoutButton());
    }

    private class UserProfileButton extends MenuItem {
        public UserProfileButton() {
            super("Profile");
        }

        @Override
        public void onClick(ActionEvent e) {
            windowManager.goToWindow(new UserProfileWindow(loggedInUser, mainWindow, userController));
        }
    }

    private class UserMenuListUsersButton extends MenuItem {
        public UserMenuListUsersButton() {
            super("List users");
        }

        @Override
        public void onClick(ActionEvent e) {
            windowManager.goToWindow(new ListUsersWindow(userController, loggedInUser, mainWindow));
        }
    }

    private class UserMenuLogoutButton extends MenuItem {
        public UserMenuLogoutButton() {
            super("Logout");
        }

        @Override
        public void onClick(ActionEvent e) {
            userController.logout();
        }
    }
}
