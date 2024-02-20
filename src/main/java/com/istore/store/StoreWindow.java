package com.istore.store;

import com.istore.WindowManager;
import com.istore.user.User;

import javax.swing.*;
import java.awt.*;

public class  StoreWindow extends JPanel {

    private JLabel storeNameLabel;

    public StoreWindow(Store store, StoreController storeController, WindowManager windowManager, User loggedinUser) {
        setLayout(new BorderLayout());
        storeNameLabel = new JLabel(store.getName());
        add(storeNameLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new BorderLayout());

        JTable tableitems = new JTable(new ItemsTableModel(store.getInventory().getItemsList(), loggedinUser));
        ;

        panel.add(new JScrollPane(tableitems), BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
    }
}