package com.istore;

import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class WindowManager {

    private final JFrame frame;
    private final JPanel windowPanel;
    private final List<JPanel> historyPanels = new ArrayList<>();
    private JPanel currentWindowPanel;

    public void goToWindow(JPanel panel) {
        historyPanels.add(panel);
        goToWindowWithoutHistory(panel);
    }

    public void goToWindowWithoutHistory(JPanel panel) {
        if (currentWindowPanel != null) {
            windowPanel.remove(currentWindowPanel);
        }
        currentWindowPanel = panel;
        windowPanel.add(currentWindowPanel, BorderLayout.CENTER);
        windowPanel.revalidate();
        windowPanel.repaint();
    }

    public void backToPreviousWindow() {
        if (historyPanels.size() > 2) {
            historyPanels.remove(historyPanels.size() - 1);
            goToWindowWithoutHistory(historyPanels.get(historyPanels.size() - 1));
        }
    }

    public void resetHistory() {
        historyPanels.clear();
    }

    public void initializeWindow() {
        frame.setTitle("iStore");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}