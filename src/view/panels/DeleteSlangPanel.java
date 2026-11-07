package view.panels;

import java.awt.*;
import javax.swing.*;

public class DeleteSlangPanel extends JPanel {
    private JTextField inputSlang;
    private JButton deleteButton;
    
    public DeleteSlangPanel() {
        setLayout(new BorderLayout(10, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel headerLabel = new JLabel("Delete Slang Word", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        
        JLabel slangLabel = new JLabel("Slang word to delete: ");
        slangLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        slangLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.add(slangLabel);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        inputSlang = new JTextField(15);
        inputSlang.setFont(new Font("Arial", Font.PLAIN, 14));
        
        inputSlang.setMaximumSize(inputSlang.getPreferredSize());
        inputSlang.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        inputPanel.add(inputSlang);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        deleteButton = new JButton("Delete");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(deleteButton);
        inputPanel.add(buttonPanel);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(inputPanel, BorderLayout.CENTER);

        add(wrapperPanel, BorderLayout.CENTER);
    }

    public JTextField getInputSlang() {
        return inputSlang;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }
}