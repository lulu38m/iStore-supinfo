package com.istore.store;

import com.istore.inventory.Item;

import java.util.List;
import javax.swing.table.AbstractTableModel;


public class ItemsTableModel extends AbstractTableModel {
    public ItemsTableModel(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    private final List<Item> itemsList;

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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // TODO: Implement this method
    }

    public void addItem(Item item) {
        this.itemsList.add(item);
    }

    public void removeItem(Item item) {
        this.itemsList.remove(item);
    }

}
