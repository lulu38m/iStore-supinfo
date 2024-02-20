package com.istore.user;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UserListTableModel extends AbstractTableModel {

    private final List<User> users = new ArrayList<>();
    private final UserController userController;
    private final User loggedInUser;

    public UserListTableModel(UserController userController, User loggedInUser) {
        this.userController = userController;
        this.loggedInUser = loggedInUser;
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

        String originalPseudo = user.getPseudo();
        String originalEmail = user.getEmail();
        Role originalRole = user.getRole();

        switch (columnIndex) {
            case 1 -> user.setPseudo((String) aValue);
            case 2 -> user.setEmail((String) aValue);
            case 3 -> user.setRole((Role) aValue);
        }

        try {
            userController.updateUser(user);
            System.out.println("User updated");
        } catch (IllegalArgumentException e) {
            // Show error message and revert the change

            user.setPseudo(originalPseudo);
            user.setEmail(originalEmail);
            user.setRole(originalRole);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // The id can't be edited and only admin can change the fields
        return columnIndex != 0 && loggedInUser.getRole().equals(Role.ADMIN);
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
