package view.panels;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AddSlangPanel extends JPanel {
    private JTextField slangInput;
    private JTextField definitionInput;
    private JButton addButton;

    public AddSlangPanel() {
        slangInput = new JTextField(20);
        definitionInput = new JTextField(20);
        addButton = new JButton("Add Slang");

        setLayout(new BorderLayout(0, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel TitlePanel = new JPanel();
        TitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Add New Slang Word");
        titleLabel.setFont(titleLabel.getFont().deriveFont(16.0f));
        TitlePanel.add(titleLabel);

          
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Slang Word:"));
        inputPanel.add(slangInput);
        inputPanel.add(new JLabel("Definition:"));
        inputPanel.add(definitionInput);

        add(TitlePanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);
    }

    public JTextField getSlangInput() {
        return slangInput;
    }

    public JTextField getDefinitionInput() {
        return definitionInput;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
}