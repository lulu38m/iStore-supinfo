package com.iStore.account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginOrCreateWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton actionButton;
    private JButton switchButton;
    private boolean isLogin = true;

    public LoginOrCreateWindow() {
        setTitle("Connexion ou Création");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        usernameField = new JTextField(15); // Set preferred columns
        passwordField = new JPasswordField(15); // Set preferred columns
        actionButton = new JButton("Se connecter");
        switchButton = new JButton("Basculer vers la création");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Nom d'utilisateur:"));
        panel.add(usernameField);
        panel.add(new JLabel("Mot de passe:"));
        panel.add(passwordField);
        panel.add(actionButton);
        panel.add(switchButton);

        add(panel);

        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchMode();
            }
        });

        updateMode();
    }

    private void switchMode() {
        isLogin = !isLogin;
        updateMode();
    }

    private void updateMode() {
        if (isLogin) {
            actionButton.setText("Se connecter");
            switchButton.setText("Basculer vers la création");
        } else {
            actionButton.setText("Créer");
            switchButton.setText("Basculer vers la connexion");
        }
    }
}
