package com.istore.user;

import javax.swing.*;

public class LoginWindow extends JPanel {
    private final UserController userController;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginWindow(UserController userController) {
        this.userController = userController;
        this.initializeWindow();
    }

    private void initializeWindow() {
        usernameField = new JTextField(15); // Set preferred columns
        passwordField = new JPasswordField(15); // Set preferred columns
        loginButton = new JButton("Se connecter");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Nom d'utilisateur:"));
        panel.add(usernameField);
        panel.add(new JLabel("Mot de passe:"));
        panel.add(passwordField);

        // Add the action button in the center of the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        panel.add(buttonPanel);
        add(panel);
    }
}
