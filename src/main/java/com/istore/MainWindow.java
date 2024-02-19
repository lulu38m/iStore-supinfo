package com.istore;

import com.istore.inventory.InventoryController;
import com.istore.menu.AdminMenu;
import com.istore.menu.BackButton;
import com.istore.menu.MenuItem;
import com.istore.menu.UserMenu;
import com.istore.store.ListStoreWindow;
import com.istore.user.*;
import com.istore.store.StoreController;
import com.istore.store.StoreModel;
import com.istore.user.LoginOrCreateWindow;
import com.istore.user.User;
import com.istore.user.UserController;
import com.istore.user.UserLoginEventsListener;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame implements UserLoginEventsListener {

    private final WindowManager windowManager;
    private final UserController userController;
    private final JLabel userLabel;
    private User loggedInUser;
    private final StoreController storeController;
    private InventoryController inventory;


    public MainWindow(UserController userController, StoreController storeController) {
        this.storeController = storeController;

        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));
        userLabel = new JLabel();
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userLabel.setVerticalAlignment(SwingConstants.CENTER);
        windowPanel.add(userLabel);

        this.userController = userController;

        this.windowManager = new WindowManager(this, windowPanel);
        this.windowManager.initializeWindow();

        windowManager.goToWindow(new LoginOrCreateWindow(userController));
        add(windowPanel);

        userController.getUserModel().subscribe(this);
    }

    @Override
    public void onLogin(User user) {
        loggedInUser = user;
        userLabel.setText("Hello, " + user.getPseudo() + "!");

        updateMenuBar();

        windowManager.goToWindow(new ListStoreWindow(storeController, inventory, windowManager));
    }

    @Override
    public void onLogout() {
        loggedInUser = null;
        userLabel.setText("");
        windowManager.goToWindow(new LoginOrCreateWindow(userController));
        updateMenuBar();
    }

    private void updateMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        if (loggedInUser == null) {
            setJMenuBar(menuBar);
            return;
        }
        JMenu userMenu = new UserMenu(userController);

        if (loggedInUser.getRole().equals(Role.ADMIN)) {
            JMenu adminMenu = new AdminMenu();
            menuBar.add(adminMenu);
        }

        menuBar.add(userMenu);

        BackButton backButton = new BackButton(windowManager);
        menuBar.add(backButton);

        setJMenuBar(menuBar);
    }
}
