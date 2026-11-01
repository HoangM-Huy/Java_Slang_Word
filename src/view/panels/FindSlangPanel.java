package view.panels;

import java.awt.*;
import javax.swing.*;

public class FindSlangPanel extends JPanel {
    private JTextField slangInput;
    private JButton searchButton;
    private JTextArea resultArea;

    public FindSlangPanel() {
        setLayout(new BorderLayout());

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

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
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