package com.istore.menu;

import com.istore.WindowManager;
import com.istore.store.Store;
import com.istore.store.StoreController;
import com.istore.user.ListUsersWindow;
import com.istore.user.User;
import com.istore.user.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UserMenu extends JMenu {

    private final UserController userController;
    private final WindowManager windowManager;
    private final User loggedInUser;
    private final StoreController storeController;

    public UserMenu(UserController userController, User loggedInUser, WindowManager windowManager, StoreController storeController) {
        super("User");
        this.userController = userController;
        this.loggedInUser = loggedInUser;
        this.windowManager = windowManager;
        this.storeController = storeController;

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
            windowManager.goToWindow(new ListUsersWindow(userController, loggedInUser, storeController));
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
