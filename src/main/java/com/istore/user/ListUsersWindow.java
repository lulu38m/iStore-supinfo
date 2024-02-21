package com.istore.user;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class ListUsersWindow extends JPanel {

    private final UserListTableModel userListTableModel;

    public ListUsersWindow(UserController userController, User loggedInUser, UserLoginEventsListener listener) {
        this.userListTableModel = new UserListTableModel(userController, loggedInUser, listener);
        userListTableModel.addUsers(userController.getUserModel().getUsersList());
        this.initializeWindow();
    }

    private void initializeWindow() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable table = new JTable(this.userListTableModel);
        table.setRowHeight(30);

        TableColumn col = table.getColumnModel().getColumn(3);
        col.setCellEditor(new DefaultCellEditor(new JComboBox<>(Role.values())));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(575, 250));

        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);
    }

}
