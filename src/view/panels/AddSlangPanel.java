package view.panels;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class AddSlangPanel extends JPanel {
    private static final Color ACCENT = new Color(52, 120, 246);
    private static final Color MUTED = new Color(120, 120, 120);

    private JTextField slangInput;
    private JTextField definitionInput;
    private JButton addButton;

    public AddSlangPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel title = new JLabel("Add Slang Word");;
        title.setFont(new Font("Arial", Font.BOLD, 22));
        JPanel titleContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleContainer.add(title);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        form.add(titleContainer);
        form.add(Box.createRigidArea(new Dimension(0, 10)));

        slangInput = createTextField();
        form.add(createLabel("Slang Word"));
        form.add(Box.createRigidArea(new Dimension(0, 6)));
        form.add(slangInput);
        form.add(Box.createRigidArea(new Dimension(0, 18)));

        definitionInput = createTextField();
        form.add(createLabel("Definition"));
        form.add(Box.createRigidArea(new Dimension(0, 6)));
        form.add(definitionInput);
        form.add(Box.createRigidArea(new Dimension(0, 6)));

        addButton = new JButton("Add Slang");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton.setPreferredSize(new Dimension(140, 38));
        addButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        form.add(addButton);

        add(form, BorderLayout.NORTH);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setPreferredSize(new Dimension(300, 36));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

        Border line = BorderFactory.createLineBorder(new Color(200, 200, 200));
        Border padding = BorderFactory.createEmptyBorder(4, 10, 4, 10);
        field.setBorder(BorderFactory.createCompoundBorder(line, padding));
        return field;
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