package com.istore.user;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UserListTableModel extends AbstractTableModel {

    private final List<User> users = new ArrayList<>();
    private final UserController userController;

    public UserListTableModel(UserController userController) {
        this.userController = userController;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }


    @Override
    public String getColumnName(int col) {
        return switch (col) {
            case 0 -> "ID";
            case 1 -> "Pseudo";
            case 2 -> "Email";
            case 3 -> "Role";
            default -> "";
        };
    }

    @Override
    public Object getValueAt(int row, int col) {
        User user = users.get(row);
        return switch (col) {
            case 0 -> user.getId().toString();
            case 1 -> user.getPseudo();
            case 2 -> user.getEmail();
            case 3 -> user.getRole().toString();
            default -> "";
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case 1 -> user.setPseudo((String) aValue);
            case 2 -> user.setEmail((String) aValue);
            case 3 -> user.setRole(Role.valueOf((String) aValue));
        }

        userController.updateUser(user);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Edit only the pseudo, email, and role
        return columnIndex != 0;
    }

    public void addUser(User user) {
        this.users.add(user);

        fireTableDataChanged();
    }

    public void addUser(List<User> userList) {
        this.users.addAll(userList);
        this.fireTableDataChanged();
    }
}
