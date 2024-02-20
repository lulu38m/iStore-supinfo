package com.istore.menu;

import com.istore.WindowManager;
import com.istore.user.ListUsersWindow;
import com.istore.user.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UserMenu extends JMenu {

    private final UserController userController;
    private final WindowManager windowManager;

    public UserMenu(UserController userController, WindowManager windowManager) {
        super("User");
        this.userController = userController;
        this.windowManager = windowManager;

        add(new JMenuItem("Profile"));
        add(new UserMenuListUsersButton());
        add(new UserMenuLogoutButton());
    }

    private class UserMenuListUsersButton extends MenuItem {
        public UserMenuListUsersButton() {
            super("List users");
        }

        @Override
        public void onClick(ActionEvent e) {
            windowManager.goToWindow(new ListUsersWindow(userController));
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
