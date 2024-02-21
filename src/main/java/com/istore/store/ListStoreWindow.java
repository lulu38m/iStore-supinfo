package com.istore.store;

import com.istore.WindowManager;
import com.istore.inventory.Inventory;
import com.istore.inventory.InventoryController;
import com.istore.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ListStoreWindow extends JPanel implements StoreListener {

    private final StoreController storeController;

    private final InventoryController inventoryController;
    private final JPanel storesPanel;
    private final WindowManager windowManager;
    private final User loggedinUser;

    public ListStoreWindow(StoreController storeController, InventoryController inventoryController, WindowManager windowManager, User loggedinUser) {
        this.storeController = storeController;
        this.inventoryController = inventoryController;
        this.storesPanel = new JPanel(new GridLayout(storeController.getStoresList().size(), 1));
        this.windowManager = windowManager;
        this.loggedinUser = loggedinUser;
        initializeWindow();
        storeController.addStoreListener(this);
    }

    private void initializeWindow() {
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Liste des magasins");
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        for (Store store : storeController.getStoresList()) {
            JButton storeButton = new JButton(store.getName());
            storeButton.addActionListener(e -> storeButtonAction(e, store));
            storesPanel.add(storeButton);
        }
        JScrollPane storesScrollPane = new JScrollPane(storesPanel);
        add(storesScrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField storeNameField = new JTextField();
        inputPanel.add(storeNameField, BorderLayout.CENTER);

        JButton addButton = new JButton("Ajouter un magasin");
        addButton.addActionListener(e -> {
            String storeName = storeNameField.getText().trim();
            if (!storeName.isEmpty()) {
                Inventory inventory = inventoryController.addInventory(new ArrayList<>());
                storeController.addStore(new Store(storeName, String.valueOf(storeController.getStoresList().size() + 1), inventory));
                storeNameField.setText("");
            }
        });
        inputPanel.add(addButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);
    }

    @Override
    public void storeAdded(Store store) {
        JButton storeButton = new JButton(store.getName());
        storesPanel.add(storeButton);
        storeButton.addActionListener(e -> storeButtonAction(e, store));
        revalidate();
        repaint();
    }

    private void storeButtonAction(ActionEvent e, Store store) {
        windowManager.goToWindow(new StoreWindow(store, storeController, windowManager, loggedinUser));
    }
}