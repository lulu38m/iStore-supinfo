package com.istore;

import lombok.RequiredArgsConstructor;

import javax.swing.*;

@RequiredArgsConstructor
public class WindowManager {

    private final JFrame frame;
    private final JPanel windowPanel;
    private JPanel currentWindowPanel;

    public void changeCurrentWindow(JPanel panel) {
        if (currentWindowPanel != null) {
            windowPanel.remove(currentWindowPanel);
        }
        currentWindowPanel = panel;
        windowPanel.add(currentWindowPanel);
        windowPanel.revalidate();
        windowPanel.repaint();
    }

    public void initializeWindow() {
        frame.setTitle("iStore");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
