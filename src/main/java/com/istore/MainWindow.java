package com.istore;

import com.istore.menu.AdminMenu;
import com.istore.menu.UserMenu;
import com.istore.store.ListStoreWindow;
import com.istore.user.*;

import javax.swing.*;

public class MainWindow extends JFrame implements UserLoginEventsListener {

    private final WindowManager windowManager;
    private final UserController userController;
    private final JLabel userLabel;
    private User loggedInUser;

    public MainWindow(UserController userController) {
        this.userController = userController;

        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));
        userLabel = new JLabel();
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userLabel.setVerticalAlignment(SwingConstants.CENTER);
        windowPanel.add(userLabel);

        this.windowManager = new WindowManager(this, windowPanel);
        this.windowManager.initializeWindow();

        windowManager.changeCurrentWindow(new LoginOrCreateWindow(userController));

        add(windowPanel);

        userController.getUserModel().subscribe(this);
    }

    @Override
    public void onLogin(User user) {
        loggedInUser = user;
        userLabel.setText("Hello, " + user.getPseudo() + "!");

        updateMenuBar();

        windowManager.changeCurrentWindow(new ListStoreWindow());
    }

    @Override
    public void onLogout() {
        System.out.println("Logout");
        loggedInUser = null;
        userLabel.setText("");
        windowManager.changeCurrentWindow(new LoginOrCreateWindow(userController));
    }

    private void updateMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu userMenu = new UserMenu(userController);

        if (loggedInUser.getRole().equals(Role.ADMIN)) {
            JMenu adminMenu = new AdminMenu();
            menuBar.add(adminMenu);
        }

        menuBar.add(userMenu);
        setJMenuBar(menuBar);
    }
}
