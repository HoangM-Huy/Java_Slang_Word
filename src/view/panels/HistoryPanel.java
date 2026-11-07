package view.panels;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.History;

public class HistoryPanel extends JPanel {
    private JList<String> historyArea;
    private DefaultListModel<String> historyModel;
    private JButton clearHistoryButton;

    public HistoryPanel() {
        setLayout(new BorderLayout(0, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("Search History", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(16.0f));
        add(titleLabel, BorderLayout.NORTH);

        historyModel = new DefaultListModel<>();
        historyArea = new JList<>(historyModel);
        historyArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane historyScrollPane = new JScrollPane(historyArea);
        historyScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        historyScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(historyScrollPane, BorderLayout.CENTER);

        clearHistoryButton = new JButton("Clear History");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(clearHistoryButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setHistory(String[] history) {
        historyModel.clear();
        for (String entry : history) {
            historyModel.addElement(entry);
        }
    }

    public void addHistory(String entry) {
        historyModel.addElement(entry);
    }

    public void loadHistoryFromFile() {
        History.loadHistoryFromFile();
        setHistory(History.getHistory().toArray(new String[0]));
    }

    public void setDeleteHistoryListener(ActionListener listener) {
        clearHistoryButton.addActionListener(listener);
    }
}