package com.istore.user;

import com.istore.jtable.ButtonColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WhitelistUserWindow extends JPanel {

    private final WhitelistUserController whitelistUserController;
    private final WhitelistedUserListTableModel whitelistedUserListTableModel;
    private final User loggedInUser;

    public WhitelistUserWindow(WhitelistUserController whitelistUserController, User loggedInUser) {
        this.whitelistUserController = whitelistUserController;
        this.loggedInUser = loggedInUser;
        this.whitelistedUserListTableModel = new WhitelistedUserListTableModel();
        whitelistedUserListTableModel.addWhitelists(whitelistUserController.listWhitelists());
        initializeWindow();
    }

    private void initializeWindow() {
        JTable table = new JTable(this.whitelistedUserListTableModel);
        table.setLayout(new BorderLayout());
        table.setRowHeight(30);

        new ButtonColumn(table, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int modelRow = Integer.parseInt(e.getActionCommand());
                Whitelist removedWhitelist = whitelistedUserListTableModel.removeWhitelist(modelRow);
                try {
                    whitelistUserController.removeWhitelistedEmail(removedWhitelist.getEmail());
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(WhitelistUserWindow.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }, 2);

        // Add an input field to add a new whitelisted email and a button to submit
        JPanel inputPanel = new JPanel(new FlowLayout());
        JTextField emailField = new JTextField(20);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            try {
                Whitelist whitelist = whitelistUserController.addWhitelistedEmail(loggedInUser, emailField.getText());
                whitelistedUserListTableModel.addWhitelist(whitelist);

                emailField.setText("");
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        inputPanel.add(emailField);
        inputPanel.add(addButton);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(575, 250));

        add(scrollPane, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.SOUTH);

    }

}
