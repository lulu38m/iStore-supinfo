package com.istore.user;

import com.istore.store.StoreController;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class ListUsersWindow extends JPanel {

    private final UserController userController;
    private final UserListTableModel userListTableModel;

    private final StoreController storeController;


    public ListUsersWindow(UserController userController, User loggedInUser, StoreController storeController) {
        this.userController = userController;
        this.storeController = storeController;
        this.userListTableModel = new UserListTableModel(userController, storeController, loggedInUser);
        this.initializeWindow();
    }

    private void initializeWindow() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable table = new JTable(this.userListTableModel);
        table.setRowHeight(30);

        TableColumn col = table.getColumnModel().getColumn(3);
        col.setCellEditor(new DefaultCellEditor(new JComboBox<>(Role.values())));

        TableColumn storeCol = table.getColumnModel().getColumn(4);
        storeCol.setCellEditor(new DefaultCellEditor(new JComboBox<>(storeController.getStoresList().toArray())));

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        for (User user : userController.getUserModel().getUsersList()) {
            userListTableModel.addUser(user);
        }

        add(panel);
    }

}
