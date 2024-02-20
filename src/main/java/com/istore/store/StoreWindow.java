package com.istore.store;

import com.istore.WindowManager;
import com.istore.inventory.InventoryController;
import com.istore.inventory.Item;
import com.istore.inventory.ItemController;
import com.istore.jtable.ButtonColumn;
import com.istore.user.User;

import javax.swing.*;
import java.awt.*;

public class  StoreWindow extends JPanel {

    private JLabel storeNameLabel;
    private ItemController itemController;

    public StoreWindow(Store store, StoreController storeController, WindowManager windowManager, User loggedinUser) {
        setLayout(new BorderLayout());
        storeNameLabel = new JLabel(store.getName());
        add(storeNameLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new BorderLayout());

        ItemsTableModel tableModel = new ItemsTableModel(store.getInventory().getItemsList(), loggedinUser);
        JTable tableitems = new JTable(tableModel);

        new ButtonColumn(tableitems, new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int modelRow = Integer.valueOf(e.getActionCommand());
                Item item = tableModel.removeItem(modelRow);
                itemController.removeItem(item);
            }
        }, 3);

        panel.add(new JScrollPane(tableitems), BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
    }
}