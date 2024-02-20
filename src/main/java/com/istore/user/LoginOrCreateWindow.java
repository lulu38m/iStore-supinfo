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
        panel.add(new Label("Vous n'avez pas de compte?"));
        JButton createAccountButton = new JButton("Créer un compte");
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
        panel.add(new Label("Vous avez déjà un compte?"));
        JButton loginButton = new JButton("Se connecter");
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            changeWindowToLogin();
        });

        return panel;
    }
}
