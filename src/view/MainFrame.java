package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.*;
import view.components.*;

public class MainFrame extends JFrame {
    private JPanel mainContainer;
    private CardLayout cardLayout;

    public MainFrame() {
        setTitle("Slang Dictionary");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
       cardLayout = new CardLayout();
       mainContainer = new JPanel(cardLayout);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        HeaderPanel header = new HeaderPanel();
        SidebarPanel sidebar = new SidebarPanel(this::navigateTo);

        add(header, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(mainContainer, BorderLayout.CENTER);
    }

    public void navigateTo(String PanelName) {
        cardLayout.show(mainContainer, PanelName);
    }
}