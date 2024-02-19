package com.istore;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class WindowManager {

    private final JFrame frame;
    private final JPanel windowPanel;
    private JPanel currentWindowPanel;

    private final List<JPanel> historyPanels = new ArrayList<>();

    public void goToWindow(JPanel panel) {
        historyPanels.add(panel);
        goToWindowWithoutHistory(panel);
    }

    public void goToWindowWithoutHistory(JPanel panel) {
        if (currentWindowPanel != null) {
            windowPanel.remove(currentWindowPanel);
        }
        currentWindowPanel = panel;
        windowPanel.add(currentWindowPanel);
        windowPanel.revalidate();
        windowPanel.repaint();
    }

    public void backToPreviousWindow() {
        if (historyPanels.size() > 2) {
            System.out.println(historyPanels.size());
            historyPanels.remove(historyPanels.size() - 1);
            System.out.println(historyPanels.size());
            goToWindowWithoutHistory(historyPanels.get(historyPanels.size() - 1));
        }
    }

    public void initializeWindow() {
        frame.setTitle("iStore");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}