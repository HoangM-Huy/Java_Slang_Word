package view.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class HeaderPanel extends JPanel {
    public HeaderPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1000, 60));

        JLabel title = new JLabel("Slang Dictionary");

        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(new EmptyBorder(20, 0, 0, 0));
        title.setFont(title.getFont().deriveFont(24f));
        setBackground(new Color(10, 150, 255));

        add(title, BorderLayout.NORTH);
    }
}