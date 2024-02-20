package com.istore.user;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class LoginWindow extends JPanel {
    private final UserController userController;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginWindow(UserController userController) {
        this.userController = userController;
        setAlignmentX(Component.CENTER_ALIGNMENT);
        this.initializeWindow();
    }

    private void initializeWindow() {
        emailField = new JTextField(15); // Set preferred columns
        passwordField = new JPasswordField(15); // Set preferred columns
        loginButton = new JButton("Se connecter");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);

        JPanel loginPanel = new JPanel();
        panel.add(new JLabel("Adresse mail:"));
        panel.add(emailField);
        panel.add(new JLabel("Mot de passe:"));
        panel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(loginButton);

        loginButton.addActionListener(e -> handleLogin());

        panel.add(loginPanel);
        panel.add(loginButton);
        add(panel);
    }

    private void handleLogin() {
        if (emailField.getText().isEmpty() || passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Optional<User> user = userController.getUserByEmail(emailField.getText());
        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isPasswordCorrect = userController.checkUserLogins(user.get(), emailField.getText(), String.valueOf(passwordField.getPassword()));
        if (!isPasswordCorrect) {
            JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Connexion réussie", "Succès", JOptionPane.INFORMATION_MESSAGE);
        userController.login(user.get());
    }
}
