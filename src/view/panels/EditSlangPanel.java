package view.panels;
import java.awt.*;
import javax.swing.*;

public class EditSlangPanel extends JPanel {
    private JTextField slangInput;
    private JTextArea definitionInput;
    private JButton fetchButton;
    private JButton updateButton;

    public EditSlangPanel() {
        setLayout(new BorderLayout(10, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));


        JLabel headerLabel = new JLabel("Edit Slang Word", SwingConstants.CENTER);
        JPanel headerContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerContainer.add(headerLabel);
        add(headerContainer, BorderLayout.NORTH);
        add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel slangLabel = new JLabel("Slang Word to Edit:");
        slangLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(slangLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel slangSubPanel = new JPanel(new BorderLayout(5, 0));
        slangInput = new JTextField();
        slangInput.setFont(new Font("Arial", Font.PLAIN, 14));

        fetchButton = new JButton("Load Current Def");
        fetchButton.setFont(new Font("Arial", Font.BOLD, 12));
        fetchButton.setFocusPainted(false);

        slangSubPanel.add(slangInput, BorderLayout.CENTER);
        slangSubPanel.add(fetchButton, BorderLayout.EAST);
        formPanel.add(slangSubPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel defLabel = new JLabel("New Definition:");
        defLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(defLabel);

        definitionInput = new JTextArea(4, 25);
        definitionInput.setFont(new Font("Arial", Font.PLAIN, 14));
        definitionInput.setLineWrap(true);
        definitionInput.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(definitionInput);
        formPanel.add(scrollPane);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        updateButton = new JButton("Save Changes");
        updateButton.setFont(new Font("Arial", Font.BOLD, 13));
        updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateButton.setFocusPainted(false);
        formPanel.add(updateButton);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(formPanel, BorderLayout.NORTH);

        add(wrapperPanel, BorderLayout.CENTER);
    }

    public JTextField getSlangInput() {
        return slangInput;
    }

    public JTextArea getDefinitionInput() {
        return definitionInput;
    }

    public JButton getFetchButton() {
        return fetchButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }
}
