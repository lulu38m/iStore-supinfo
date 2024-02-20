package com.istore.user;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class WhitelistedUserListTableModel extends AbstractTableModel {

    private final List<Whitelist> whitelists = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return whitelists.size();
    }


    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "ID";
            case 1:
                return "Email";
            case 2:
                return "Actions";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        Whitelist whitelist = whitelists.get(row);
        switch (col) {
            case 0:
                return whitelist.getId().toString();
            case 1:
                return whitelist.getEmail();
            case 2:
                return "Remove";
            default:
                return "";
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 2;
    }

    public void addWhitelist(Whitelist whitelist) {
        this.whitelists.add(whitelist);
        fireTableDataChanged();
    }

    public void addWhitelists(List<Whitelist> whitelists) {
        this.whitelists.addAll(whitelists);
        fireTableDataChanged();
    }

    public Whitelist removeWhitelist(int row) {
        Whitelist whitelist = this.whitelists.remove(row);
        fireTableDataChanged();
        return whitelist;
    }

}
