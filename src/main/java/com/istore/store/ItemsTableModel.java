package com.istore.store;

import com.istore.inventory.Item;
import com.istore.user.User;
import com.istore.user.Role;
import lombok.Getter;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ItemsTableModel extends AbstractTableModel {
    private final List<Item> itemsList;

    private final User loggedinUser;

    public ItemsTableModel(List<Item> itemsList, User loggedinUser) {
        this.itemsList = itemsList;
        this.loggedinUser = loggedinUser;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Name";
            case 1:
                return "Price";
            case 2:
                return "Quantity";
            default:
                return "";
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return itemsList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item item = itemsList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getName();
            case 1:
                return item.getPrice();
            case 2:
                return item.getQuantity();
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Item item = itemsList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                item.setName((String) aValue);
                break;
            case 1:
                item.setPrice(Integer.parseInt((String) aValue));
                break;
            case 2:
                int newQuantity = Integer.parseInt((String) aValue);
                item.setQuantity(newQuantity > 0 ? newQuantity : 1);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (loggedinUser.getRole() == Role.ADMIN) {
            return true;
        } else if (loggedinUser.getRole() == Role.USER && columnIndex == 2) {
            return true;
        }
        return false;
    }
}