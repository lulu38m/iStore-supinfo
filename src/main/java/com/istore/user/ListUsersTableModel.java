package com.istore.user;

import com.istore.store.Store;
import com.istore.store.StoreController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListUsersTableModel extends AbstractTableModel {

    private final List<User> users = new ArrayList<>();
    private final UserController userController;
    private final StoreController storeController;
    private final User loggedInUser;
    private final UserLoginEventsListener listener;

    public ListUsersTableModel(UserController userController, StoreController storeController, User loggedInUser, UserLoginEventsListener listener) {
        this.userController = userController;
        this.loggedInUser = loggedInUser;
        this.listener = listener;
        this.storeController = storeController;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }


    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "ID";
            case 1:
                return "Pseudo";
            case 2:
                return "Email";
            case 3:
                return "Role";
            case 4:
                return "Stores";
            case 5:
                return "Actions";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        User user = users.get(row);
        switch (col) {
            case 0:
                return user.getId().toString();
            case 1:
                return user.getPseudo();
            case 2:
                return user.getEmail();
            case 3:
                return user.getRole().toString();
            case 4:
                return user.getStores().stream().map(Store::getName).collect(Collectors.joining(", "));
            case 5:
                return "Delete";
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);

        String originalPseudo = user.getPseudo();
        String originalEmail = user.getEmail();
        Role originalRole = user.getRole();

        switch (columnIndex) {
            case 1:
                user.setPseudo((String) aValue);
                break;
            case 2:
                user.setEmail((String) aValue);
                break;
            case 3:
                user.setRole((Role) aValue);
                break;
            case 4:
                user.setStores(storeController.getStoresList().stream().filter(store -> ((String) aValue).contains(store.getName())).collect(Collectors.toList()));
                break;
        }

        try {
            userController.updateUser(user);
            listener.onUpdate(user);
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

    public void addUsers(List<User> userList) {
        this.users.addAll(userList);
        this.fireTableDataChanged();
    }

    public User deleteUser(int rowIndex) {
        User removedUser = users.remove(rowIndex);
        this.fireTableDataChanged();
        return removedUser;
    }
}
