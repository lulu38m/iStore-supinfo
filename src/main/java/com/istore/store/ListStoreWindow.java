package com.istore.store;

import com.istore.WindowManager;

import javax.swing.*;
import java.awt.*;

public class ListStoreWindow extends JPanel implements StoreListener {

    private final StoreController storeController;
    private final JPanel storesPanel;
    private final WindowManager windowManager;

    public ListStoreWindow(StoreController storeController, WindowManager windowManager) {
        this.storeController = storeController;
        this.storesPanel = new JPanel(new GridLayout(storeController.getStoresList().size(), 1));
        this.windowManager = windowManager;
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
            storeButton.addActionListener(e -> {
                windowManager.goToWindow(new StoreWindow(store, storeController, windowManager));
            });
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
                storeController.addStore(new Store(storeName, String.valueOf(storeController.getStoresList().size() + 1)));
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
        revalidate();
        repaint();
    }
}