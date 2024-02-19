package com.istore.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class MenuItem extends JMenuItem {
    public MenuItem(String text) {
        super(text);
        addActionListener(this::onClick);
    }

    public abstract void onClick(ActionEvent e);
}
