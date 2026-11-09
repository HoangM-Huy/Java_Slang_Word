package view.panels;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FindDefinitionPanel extends JPanel{
    private JTextArea definitionInput;
    private JTextArea slangOutput;
    private JButton searchButton;

    public FindDefinitionPanel() {
        setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Find Slang by Definition");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.NORTH);
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.add(headerLabel);

        definitionInput = new JTextArea(5, 20);
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
        outputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        slangOutput = new JTextArea(6, 20);
        slangOutput.setEditable(false);
        slangOutput.setLineWrap(true);
        slangOutput.setWrapStyleWord(true);
        slangOutput.setMargin(new Insets(5, 8, 5, 8));

        JScrollPane outputScroll = new JScrollPane(slangOutput);
        outputScroll.setMaximumSize(outputScroll.getPreferredSize());
        outputScroll.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        outputPanel.add(outputLabel);
        outputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        outputPanel.add(outputScroll);
        outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));

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

    public JTextArea getSlangOutput() {
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

    public void setSlangOutput(String slangWords) {
        slangOutput.setText(slangWords);
        slangOutput.setCaretPosition(0);
    }
}