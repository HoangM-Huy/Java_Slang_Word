package view.components;

import java.awt.*;
import javax.swing.*;

public class HeaderPanel extends JPanel {
    public HeaderPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1000, 60));

        JLabel title = new JLabel("Slang Dictionary");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.WEST);
    }
}