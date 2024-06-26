package com.istore.user;

import javax.swing.*;

public class CreateAccountWindow extends JPanel {
    private final UserController userController;
    private final LoginOrCreateWindow parentWindow;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public CreateAccountWindow(UserController userController, LoginOrCreateWindow parentWindow) {
        this.userController = userController;
        this.parentWindow = parentWindow;
        this.initializeWindow();
    }

    private void initializeWindow() {
        usernameField = new JTextField(15); // Set preferred columns
        emailField = new JTextField(15); // Set preferred columns
        passwordField = new JPasswordField(15); // Set preferred columns
        registerButton = new JButton("Create");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Email address:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        registerButton.addActionListener(e -> handleRegister());

        // Add the action button in the center of the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerButton);
        panel.add(buttonPanel);

        add(panel);
    }

    private void handleRegister() {
        if (usernameField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            userController.createUser(emailField.getText(), usernameField.getText(), String.valueOf(passwordField.getPassword()));
            JOptionPane.showMessageDialog(this, "Account created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            parentWindow.changeWindowToLogin();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
