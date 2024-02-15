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

        loginButton.addActionListener(e -> handleLogin());

        // Add the action button in the center of the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        panel.add(buttonPanel);
        add(panel);
    }

    private void handleLogin() {
        if (usernameField.getText().isEmpty() || passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = userController.getUserByUsername(usernameField.getText());
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isPasswordCorrect = userController.checkUserLogins(user, usernameField.getText(), String.valueOf(passwordField.getPassword()));
        if (!isPasswordCorrect) {
            JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Connexion réussie", "Succès", JOptionPane.INFORMATION_MESSAGE);
    }
}
