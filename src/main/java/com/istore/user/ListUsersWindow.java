package com.istore.user;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ListUsersWindow extends JPanel {

    private final UserController userController;
    private final UserListTableModel userListTableModel;

    public ListUsersWindow(UserController userController) {
        this.userController = userController;
        this.userListTableModel = new UserListTableModel(userController);
        this.initializeWindow();
    }

    private void initializeWindow() {
        JPanel panel = new JPanel(new BorderLayout());
        JTable table = new JTable(this.userListTableModel);
        table.setDefaultRenderer(Role.class, new RoleCellRenderer());
        table.setDefaultEditor(Role.class, new RoleCellEditor(Arrays.stream(Role.values()).toList()));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        for (User user : userController.getUserModel().getUsersList()) {
            userListTableModel.addUser(user);
        }

        add(panel);
    }

}
