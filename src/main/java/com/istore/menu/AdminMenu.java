package com.istore.menu;

import com.istore.WindowManager;
import com.istore.user.User;
import com.istore.user.UserController;
import com.istore.user.WhitelistUserController;
import com.istore.user.WhitelistUserWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AdminMenu extends JMenu {

    private final WindowManager windowManager;
    private final UserController userController;
    private final WhitelistUserController whitelistUserController;
    private final User loggedInUser;

    public AdminMenu(WindowManager windowManager, UserController userController, WhitelistUserController whitelistUserController, User loggedInUser) {
        super("Admin");
        this.windowManager = windowManager;
        this.userController = userController;
        this.whitelistUserController = whitelistUserController;
        this.loggedInUser = loggedInUser;

        add(new AdminMenuWhitelistUserButton());
        add(new AdminMenuAddShopButton());
    }

    private class AdminMenuWhitelistUserButton extends MenuItem {
        public AdminMenuWhitelistUserButton() {
            super("Whitelist a user");
        }

        @Override
        public void onClick(ActionEvent e) {
            windowManager.goToWindow(new WhitelistUserWindow(whitelistUserController, loggedInUser));
        }
    }

    private class AdminMenuAddShopButton extends MenuItem {
        public AdminMenuAddShopButton() {
            super("Add a shop");
        }

        @Override
        public void onClick(ActionEvent e) {
            System.out.println("Add a shop");
        }
    }

}
