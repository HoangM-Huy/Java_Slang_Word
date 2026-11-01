package view.panels;

import java.awt.*;
import javax.swing.*;

public class FindSlangPanel extends JPanel {
    private JTextField slangInput;
    private JButton searchButton;
    private JTextArea resultArea;

    public FindSlangPanel() {
        setLayout(new BorderLayout(20, 20));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel slangLabel = new JLabel("Enter slang word:");
        slangInput = new JTextField(20);
        searchButton = new JButton("Search");

        inputPanel.add(slangLabel);
        inputPanel.add(slangInput);
        inputPanel.add(searchButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        resultArea.setMargin(new Insets(20, 20, 20, 20));

        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        scrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.GRAY));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        JPanel resultsPanel = new JPanel(new BorderLayout());
        JLabel resultsLabel = new JLabel("Search Results:");
        resultsLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        resultsPanel.add(resultsLabel, BorderLayout.NORTH);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(inputPanel, BorderLayout.NORTH);
        add(resultsPanel, BorderLayout.CENTER);
    }

    public JTextField getSlangInput() {
        return slangInput;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JTextArea getResultArea() {
        return resultArea;
    }
}