package com.istore;

import com.istore.store.ListStoreWindow;
import com.istore.store.StoreController;
import com.istore.user.LoginOrCreateWindow;
import com.istore.user.UserController;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow(UserController userController, StoreController storeController) {
        this.initializeWindow();
        add(new ListStoreWindow(storeController ));
    }

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
