package view.panels;

import java.awt.*;
import javax.swing.*;

public class RandomSlangPanel extends JPanel {
    private JTextField randomSlang;
    private JTextArea randomDef;
    private JButton randomButton;

    public RandomSlangPanel() {
        setLayout(new BorderLayout(10, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel headerLabel = new JLabel("Random Slang Word", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel randomPanel = new JPanel();
        randomPanel.setLayout(new BoxLayout(randomPanel, BoxLayout.Y_AXIS));
        
        JLabel slangLabel = new JLabel("Random Slang Word: ");
        slangLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        slangLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        randomPanel.add(slangLabel);

        JLabel definitionLabel = new JLabel("Its Definition: ");
        definitionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        definitionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        randomPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        
        randomDef = new JTextArea(5, 20);      
        randomDef.setLineWrap(true);
        randomDef.setWrapStyleWord(true);
        randomDef.setEditable(false);
        randomDef.setFont(new Font("Arial", Font.PLAIN, 14));
        randomDef.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        JScrollPane defScroll = new JScrollPane(randomDef);
        defScroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        defScroll.setMaximumSize(defScroll.getPreferredSize());
        defScroll.setAlignmentX(Component.CENTER_ALIGNMENT);

        randomSlang = new JTextField(15);
        randomSlang.setFont(new Font("Arial", Font.PLAIN, 14));

        randomSlang.setEditable(false);
        randomDef.setEditable(false);
        
        randomSlang.setMaximumSize(randomSlang.getPreferredSize());
        randomSlang.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        randomPanel.add(randomSlang);
        randomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        randomPanel.add(definitionLabel);
        randomPanel.add(defScroll);
        randomPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        randomButton = new JButton("Random");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(randomButton);
        randomPanel.add(buttonPanel);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(randomPanel, BorderLayout.CENTER);

        add(wrapperPanel, BorderLayout.CENTER);
    }

    public JTextField getRandomSlangTextField() {
        return randomSlang;
    }

    public JTextArea getRandomDefTextField() {
        return randomDef;
    }

    public JButton getRandomButton() {
        return randomButton;
    }

    public void setRandomSlang(String result) {
        randomSlang.setText(result);
    }

    public void setRandomDef(String result) {
        randomDef.setText(result);
    }
}