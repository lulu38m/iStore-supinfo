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
        registerButton = new JButton("Créer");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Nom d'utilisateur:"));
        panel.add(usernameField);
        panel.add(new JLabel("Adresse email:"));
        panel.add(emailField);
        panel.add(new JLabel("Mot de passe:"));
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
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isEmailValid = userController.validateEmail(emailField.getText());
        if (!isEmailValid) {
            JOptionPane.showMessageDialog(this, "L'adresse email n'est pas valide", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isUsernameAvailable = userController.isPseudoAvailable(usernameField.getText());
        if (!isUsernameAvailable) {
            JOptionPane.showMessageDialog(this, "Le nom d'utilisateur est déjà pris", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isEmailAvailable = userController.isEmailAvailable(emailField.getText());
        if (!isEmailAvailable) {
            JOptionPane.showMessageDialog(this, "L'adresse email est déjà utilisée", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            userController.createUser(emailField.getText(), usernameField.getText(), String.valueOf(passwordField.getPassword()));
            JOptionPane.showMessageDialog(this, "Compte créé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);

            parentWindow.changeWindowToLogin();
        } catch (UserEmailNotWhitelistedException e) {
            JOptionPane.showMessageDialog(this, "L'adresse email n'est pas autorisée", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
