package com.istore.user;

import com.istore.store.Store;
import com.istore.store.StoreController;

import com.istore.jtable.ButtonColumn;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ListUsersWindow extends JPanel {

    private final ListUsersTableModel listUsersTableModel;
    private final UserController userController;
    private final StoreController storeController;

    public ListUsersWindow(UserController userController, User loggedInUser, StoreController storeController, UserLoginEventsListener listener) {

        this.userController = userController;
        this.storeController = storeController;
        this.listUsersTableModel = new ListUsersTableModel(userController,storeController , loggedInUser, listener);
        listUsersTableModel.addUsers(userController.getUserModel().getUsersList());
        this.initializeWindow();
    }

    private void initializeWindow() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable table = new JTable(this.listUsersTableModel);
        table.setRowHeight(30);

        TableColumn col = table.getColumnModel().getColumn(3);
        col.setCellEditor(new DefaultCellEditor(new JComboBox<>(Role.values())));

        TableColumn storeCol = table.getColumnModel().getColumn(4);
        storeCol.setCellEditor(new DefaultCellEditor(new JComboBox<>(storeController.getStoresList().stream().map(Store::getName).toArray())));

        new ButtonColumn(table, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int modelRow = Integer.parseInt(e.getActionCommand());
                User removedUser = listUsersTableModel.deleteUser(modelRow);
                try {
                    userController.deleteUser(removedUser);
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(ListUsersWindow.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }, 5);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(575, 250));

        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);
    }

}
