package com.istore.store;

import com.istore.WindowManager;
import javax.swing.*;
import java.awt.*;

public class  StoreWindow extends JPanel {

    private JLabel storeNameLabel;
    private JButton backButton;

    public StoreWindow(Store store, StoreController storeController, WindowManager windowManager) {
        setLayout(new BorderLayout());
        storeNameLabel = new JLabel(store.getName());
        add(storeNameLabel, BorderLayout.NORTH);

        backButton = new JButton("Retour");
        add(backButton, BorderLayout.SOUTH);

        backButton.addActionListener(e -> windowManager.goToWindow(new ListStoreWindow(storeController, windowManager)));

    }
}