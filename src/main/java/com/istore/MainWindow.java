package com.istore;

import com.istore.user.LoginOrCreateWindow;
import com.istore.user.UserController;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow(UserController userController) {
        this.initializeWindow();

        add(new LoginOrCreateWindow(userController));
    }

    private void initializeWindow() {
        setTitle("iStore");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
