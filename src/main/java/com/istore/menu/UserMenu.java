package com.istore.menu;

import com.istore.user.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UserMenu extends JMenu {

    private final UserController userController;

    public UserMenu(UserController userController) {
        super("User");
        this.userController = userController;

        add(new JMenuItem("Profile"));
        add(new UserMenuLogoutButton());
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
