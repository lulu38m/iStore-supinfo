package com.istore.store;

import com.istore.WindowManager;
import com.istore.inventory.Item;
import com.istore.inventory.ItemController;
import com.istore.jtable.ButtonColumn;
import com.istore.style.PlaceholderTextField;
import com.istore.user.Role;
import com.istore.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StoreWindow extends JPanel {

    private final JLabel storeNameLabel;


    public StoreWindow(Store store, User loggedinUser, ItemController itemController, StoreController storeController, WindowManager windowManager) {

        setLayout(new BorderLayout());
        storeNameLabel = new JLabel(store.getName());
        add(storeNameLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new BorderLayout());

        if (loggedinUser.getRole().equals(Role.ADMIN)) {
            JButton deleteStoreButton = new JButton("Delete Store");

            deleteStoreButton.addActionListener(e -> {
                storeController.deleteStore(store);
                windowManager.backToPreviousWindow();
            });
            panel.add(deleteStoreButton, BorderLayout.NORTH);
        }


        ItemsTableModel tableModel = new ItemsTableModel(store.getInventory().getItemsList(), loggedinUser);
        JTable tableItems = new JTable(tableModel);

        new ButtonColumn(tableItems, new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int modelRow = Integer.valueOf(e.getActionCommand());
                Item item = tableModel.removeItem(modelRow);
                itemController.removeItem(item);
            }
        }, 3);

        panel.add(new JScrollPane(tableItems), BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);

        // Add item
        JPanel inputPanel = new JPanel(new GridLayout(1, 4));
        PlaceholderTextField itemNameField = new PlaceholderTextField("Name");
        PlaceholderTextField itemPriceField = new PlaceholderTextField("Price");
        PlaceholderTextField itemQuantityField = new PlaceholderTextField("Quantity");
        inputPanel.add(itemNameField);
        inputPanel.add(itemPriceField);
        inputPanel.add(itemQuantityField);
        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(e -> {
            String itemName = itemNameField.getText().trim();
            String itemPrice = itemPriceField.getText().trim();
            String itemQuantity = itemQuantityField.getText().trim();
            if (!itemName.isEmpty() && !itemPrice.isEmpty() && !itemQuantity.isEmpty()) {
                Item item = new Item(itemName, Integer.parseInt(itemPrice), Integer.parseInt(itemQuantity), store.getInventory());
                tableModel.addItem(item);
                itemController.addItem(item);
            }
        });
        inputPanel.add(addButton);
        add(inputPanel, BorderLayout.SOUTH);

    }
}