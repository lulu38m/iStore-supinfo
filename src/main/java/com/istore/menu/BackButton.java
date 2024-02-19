package com.istore.menu;

import com.istore.WindowManager;

import java.awt.event.ActionEvent;

public class BackButton extends MenuItem {
    private final WindowManager windowManager;

    public BackButton(WindowManager windowManager) {
        super("Retour");
        this.windowManager = windowManager;
    }

    @Override
    public void onClick(ActionEvent e) {
        windowManager.backToPreviousWindow();
    }
}
