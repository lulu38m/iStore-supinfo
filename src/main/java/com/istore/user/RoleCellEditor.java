package com.istore.user;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RoleCellEditor extends AbstractCellEditor
        implements TableCellEditor, ActionListener {

    private Role role;
    private List<Role> roleList;

    public RoleCellEditor(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public Object getCellEditorValue() {
        return this.role;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (value instanceof Role r) {
            this.role = r;
        }

        JComboBox<Role> comboRole = new JComboBox<>();

        for (Role aRole : roleList) {
            comboRole.addItem(aRole);
        }

        comboRole.setSelectedItem(role);
        comboRole.addActionListener(this);

        if (isSelected) {
            comboRole.setBackground(table.getSelectionBackground());
        } else {
            comboRole.setBackground(table.getSelectionForeground());
        }

        return comboRole;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox<Role> comboRole = (JComboBox<Role>) event.getSource();
        this.role = (Role) comboRole.getSelectedItem();
    }

}
