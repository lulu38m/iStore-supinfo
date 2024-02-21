package com.istore.user;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicReference;

public class UserProfileWindow extends JPanel {

    private final User loggedInUser;
    private final UserLoginEventsListener listener;
    private final UserController userController;

    public UserProfileWindow(User loggedInUser, UserLoginEventsListener listener, UserController userController) {
        this.loggedInUser = loggedInUser;
        this.listener = listener;
        this.userController = userController;
        initializeWindow();
    }

    private void initializeWindow() {
        // Save the user state before any modification to be able to restore it if the user cancel the modification
        AtomicReference<String> originalPseudo = new AtomicReference<>(loggedInUser.getPseudo());
        AtomicReference<String> originalEmail = new AtomicReference<>(loggedInUser.getEmail());

        // Set the layout of the panel
        setLayout(null);

        // Create the labels
        JLabel pseudoLabel = new JLabel("Pseudo");
        JLabel emailLabel = new JLabel("Email");
        JLabel roleLabel = new JLabel("Role");

        // Set the position and size of the labels
        pseudoLabel.setBounds(50, 50, 100, 30);
        emailLabel.setBounds(50, 100, 100, 30);
        roleLabel.setBounds(50, 150, 100, 30);

        // Add the labels to the panel
        add(pseudoLabel);
        add(emailLabel);
        add(roleLabel);

        // Create the text fields
        JTextField pseudoField = new JTextField();
        JTextField emailField = new JTextField();

        // Set the position and size of the text fields
        pseudoField.setBounds(150, 50, 200, 30);
        emailField.setBounds(150, 100, 200, 30);

        // Add the text fields to the panel
        add(pseudoField);
        add(emailField);

        // Create the buttons
        JButton editButton = new JButton("Edit");
        JButton saveButton = new JButton("Save");
        JButton deleteAccount = new JButton("Delete Account");

        // Set the position and size of the buttons
        editButton.setBounds(150, 150, 100, 30);
        saveButton.setBounds(250, 150, 100, 30);
        deleteAccount.setBounds(150, 200, 200, 30);

        // Add the buttons to the panel
        add(editButton);
        add(saveButton);
        add(deleteAccount);

        // Add an action listener to the edit button
        editButton.addActionListener(e -> {
            // Enable the text fields
            pseudoField.setEnabled(!pseudoField.isEnabled());
            emailField.setEnabled(!emailField.isEnabled());

            if (!pseudoField.isEnabled()) { // If pseudoField is disabled, emailField is also disabled
                // If the text fields are disabled, restore the original user state
                pseudoField.setText(originalPseudo.get());
                emailField.setText(originalEmail.get());
            }
        });

        // Add an action listener to the save button
        saveButton.addActionListener(e -> {
            try {
                User clonedUser = loggedInUser.clone(); // Clone the user to keep the original state in case of error
                clonedUser.setPseudo(pseudoField.getText());
                clonedUser.setEmail(emailField.getText());

                userController.updateUser(clonedUser);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Disable the text fields
            pseudoField.setEnabled(false);
            emailField.setEnabled(false);

            // Save the new user state
            originalPseudo.set(pseudoField.getText());
            originalEmail.set(emailField.getText());

            loggedInUser.setPseudo(pseudoField.getText());
            loggedInUser.setEmail(emailField.getText());

            listener.onUpdate(loggedInUser); // Trigger the onUpdate method of the main window to update the user's information and to force the update of the menu bar
            JOptionPane.showMessageDialog(null, "User updated", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        // Add an action listener to the delete account button
        deleteAccount.addActionListener(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                userController.deleteUser(loggedInUser);
                listener.onDelete(loggedInUser);
            }
        });

        // Set the text fields as not editable
        pseudoField.setEnabled(false);
        emailField.setEnabled(false);

        // Set the text fields with the user's information
        pseudoField.setText(loggedInUser.getPseudo());
        emailField.setText(loggedInUser.getEmail());

        // Set the role label with the user's role
        roleLabel.setText("Role: " + loggedInUser.getRole().getLabel());

        // Set the panel size
        setSize(400, 200);

    }
}
