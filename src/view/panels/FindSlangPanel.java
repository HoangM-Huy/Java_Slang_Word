package view.panels;
import java.awt.*;
import java.awt.event.ActionListener;
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

        JPanel inputContainer = new JPanel();
        inputContainer.setLayout(new BoxLayout(inputContainer, BoxLayout.Y_AXIS));

        JLabel inputHeader = new JLabel("Find Slang Word");
        inputHeader.setFont(new Font("Arial", Font.BOLD, 16));
        inputHeader.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        inputContainer.add(inputHeader);
        inputContainer.add(inputPanel);

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
        
        add(inputContainer, BorderLayout.NORTH);
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

    public void setSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public String getSearchInput() {
        return slangInput.getText().trim();
    }

    public void showResult(String result) {
        resultArea.setText(result);
    }
}