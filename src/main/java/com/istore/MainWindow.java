package com.istore;

import com.istore.inventory.InventoryController;
import com.istore.menu.AdminMenu;
import com.istore.menu.BackButton;
import com.istore.menu.UserMenu;
import com.istore.store.ListStoreWindow;
import com.istore.store.StoreController;
import com.istore.user.*;

import javax.swing.*;

public class MainWindow extends JFrame implements UserLoginEventsListener {

    private final WindowManager windowManager;
    private final UserController userController;
    private final WhitelistUserController whitelistUserController;
    private final JLabel userLabel;
    private final StoreController storeController;
    private final InventoryController inventoryController;
    private User loggedInUser;

    public MainWindow(UserController userController, WhitelistUserController whitelistUserController, StoreController storeController, InventoryController inventoryController) {
        this.storeController = storeController;
        this.userController = userController;
        this.whitelistUserController = whitelistUserController;
        this.inventoryController = inventoryController;

        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));
        userLabel = new JLabel();
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userLabel.setVerticalAlignment(SwingConstants.CENTER);
        windowPanel.add(userLabel);

        this.windowManager = new WindowManager(this, windowPanel);
        this.windowManager.initializeWindow();

        windowManager.goToWindow(new LoginOrCreateWindow(userController, windowManager));
        add(windowPanel);

        userController.getUserModel().subscribe(this);
    }

    @Override
    public void onLogin(User user) {
        loggedInUser = user;
        userLabel.setText("Hello, " + user.getPseudo() + "!");

        updateMenuBar();

        windowManager.goToWindow(new ListStoreWindow(storeController, inventoryController, windowManager, loggedInUser));
        updateMenuBar();
    }

    @Override
    public void onLogout() {
        loggedInUser = null;
        userLabel.setText("");
        windowManager.goToWindow(new LoginOrCreateWindow(userController, windowManager));
        updateMenuBar();
    }

    @Override
    public void onUpdate(User newUser) {
        // Update the logged in user if it's the same user
        if (loggedInUser != null && loggedInUser.getId().equals(newUser.getId())) {
            loggedInUser = newUser;
            userLabel.setText("Hello, " + newUser.getPseudo() + "!");
            updateMenuBar();
        }
    }

    private void updateMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        if (loggedInUser == null) {
            setJMenuBar(menuBar);
            return;
        }
        JMenu userMenu = new UserMenu(userController, loggedInUser, windowManager, this);

        if (loggedInUser.getRole().equals(Role.ADMIN)) {
            JMenu adminMenu = new AdminMenu(windowManager, userController, whitelistUserController, loggedInUser);
            menuBar.add(adminMenu);
        }

        menuBar.add(userMenu);

        BackButton backButton = new BackButton(windowManager);
        menuBar.add(backButton);

        setJMenuBar(menuBar);
        menuBar.updateUI();
    }
}
