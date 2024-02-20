package com.istore.user;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class ListUsersWindow extends JPanel {

    private final UserController userController;
    private final UserListTableModel userListTableModel;

    public ListUsersWindow(UserController userController, User loggedInUser) {
        this.userController = userController;
        this.userListTableModel = new UserListTableModel(userController, loggedInUser);
        this.initializeWindow();
    }

    private void initializeWindow() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable table = new JTable(this.userListTableModel);
        table.setRowHeight(30);

        TableColumn col = table.getColumnModel().getColumn(3);
        col.setCellEditor(new DefaultCellEditor(new JComboBox<>(Role.values())));

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        for (User user : userController.getUserModel().getUsersList()) {
            userListTableModel.addUser(user);
        }

        add(panel);
    }

}
