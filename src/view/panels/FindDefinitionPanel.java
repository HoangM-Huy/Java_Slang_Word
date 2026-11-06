package view.panels;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FindDefinitionPanel extends JPanel{
    private JTextArea definitionInput;
    private JTextField slangOutput;
    private JButton searchButton;

    public FindDefinitionPanel() {
        setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Find Slang by Definition");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.NORTH);
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.add(headerLabel);

        definitionInput = new JTextArea(5, 20);
        slangOutput = new JTextField(20);
        searchButton = new JButton("Search");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel defLabel = new JLabel("Definition:");
        defLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        definitionInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        definitionInput.setLineWrap(true);
        definitionInput.setMargin(new Insets(10, 10, 10, 10));
        definitionInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(searchButton);
        
        inputPanel.add(defLabel);
        inputPanel.add(definitionInput);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        inputPanel.add(buttonPanel);

        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel outputLabel = new JLabel("Slang Word(s):");
        JPanel outputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        slangOutput.setEditable(false);
        outputPanel.add(outputLabel);
        outputPanel.add(slangOutput);
        outputPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(headerPanel);
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(outputPanel);

        add(mainPanel, BorderLayout.CENTER);
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

    public void setSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public String getDefinitionText() {
        return definitionInput.getText().trim();
    }

    public void setSlangOutput(String slangWord) {
        slangOutput.setText(slangWord);
    }
}