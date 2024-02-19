package com.istore.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AdminMenu extends JMenu {
    public AdminMenu() {
        super("Admin");
        add(new AdminMenuWhitelistUserButton());
        add(new AdminMenuAddShopButton());
    }

    private class AdminMenuWhitelistUserButton extends MenuItem {
        public AdminMenuWhitelistUserButton() {
            super("Whitelist a user");
        }

        @Override
        public void onClick(ActionEvent e) {
            System.out.println("Whitelist a user");
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
