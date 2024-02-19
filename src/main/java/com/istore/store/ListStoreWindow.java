package com.istore.store;

import javax.swing.*;

public class ListStoreWindow extends JPanel {

    public ListStoreWindow() {
        this.initializeWindow();
    }

    private void initializeWindow() {
        JLabel label = new JLabel("Liste des magasins");
        add(label);
    }

}
