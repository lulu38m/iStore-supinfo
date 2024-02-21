package com.istore.user;

import com.istore.WindowManager;

import javax.swing.*;
import java.awt.*;

public class LoginOrCreateWindow extends JPanel {
    private final UserController userController;
    private final WindowManager windowManager;

    public LoginOrCreateWindow(UserController userController, WindowManager windowManager) {
        this.userController = userController;
        this.windowManager = windowManager;
        this.initializeWindow();
    }

    private void initializeWindow() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new LoginWindow(this.userController));
        add(noAccountPanel());
    }

    public void changeWindowToLogin() {
        removeAll();
        add(new LoginWindow(this.userController));
        add(noAccountPanel());
        revalidate();
        repaint();
    }

    private JPanel noAccountPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new Label("You don't have an account?"));
        JButton createAccountButton = new JButton("Create account");
        panel.add(createAccountButton);

        createAccountButton.addActionListener(e -> {
            removeAll();
            add(new CreateAccountWindow(this.userController, this));
            add(alreadyHaveAccountPanel());
            revalidate();
            repaint();
        });

        return panel;
    }

    private JPanel alreadyHaveAccountPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new Label("Already have an account?"));
        JButton loginButton = new JButton("Login");
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            changeWindowToLogin();
        });

        return panel;
    }
}
