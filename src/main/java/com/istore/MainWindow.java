package com.istore;

import com.istore.user.LoginOrCreateWindow;
import com.istore.user.User;
import com.istore.user.UserController;
import com.istore.user.UserLoginEventsListener;

import javax.swing.*;

public class MainWindow extends JFrame implements UserLoginEventsListener {

    private final WindowManager windowManager;
    private final JLabel userLabel;

    public MainWindow(UserController userController) {
        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));
        userLabel = new JLabel();
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userLabel.setVerticalAlignment(SwingConstants.CENTER);
        windowPanel.add(userLabel);

        this.windowManager = new WindowManager(this, windowPanel);
        this.windowManager.initializeWindow();

        windowManager.changeCurrentWindow(new LoginOrCreateWindow(userController));

    private void initializeWindow() {
        setTitle("iStore");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void changeCurrentWindow(JPanel panel) {
        removeAll();
        add(panel);
        revalidate();
        repaint();
    }
}
