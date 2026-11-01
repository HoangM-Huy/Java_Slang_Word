package view.panels;
import java.awt.*;
import javax.swing.*;

public class FindDefinitionPanel extends JPanel{
    private JTextArea definitionInput;
    private JTextField slangOutput;
    private JButton searchButton;

    public FindDefinitionPanel() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        definitionInput = new JTextArea(5, 20);
        slangOutput = new JTextField(20);
        searchButton = new JButton("Search");

        inputPanel.add(new JLabel("Definition:"));
        inputPanel.add(definitionInput);
        inputPanel.add(new JLabel("Slang:"));
        inputPanel.add(slangOutput);
        inputPanel.add(searchButton);

        add(inputPanel, BorderLayout.CENTER);
    }

    public JTextArea getDefinitionInput() {
        return definitionInput;
    }

    public JTextField getSlangOutput() {
        return slangOutput;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}