package view;

import controller.*;
import java.awt.*;
import javax.swing.*;
import view.components.*;
import view.panels.*;

public class MainFrame extends JFrame {
    private JPanel mainContainer;
    private CardLayout cardLayout;
    private AppController controller;

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

       FindSlangPanel findSlangPanel = new FindSlangPanel();
       FindDefinitionPanel findDefinitionPanel = new FindDefinitionPanel();
       HistoryPanel historyPanel = new HistoryPanel();

       mainContainer.add(findSlangPanel, "FINDSLANG");
       mainContainer.add(findDefinitionPanel, "FINDDEF");
       mainContainer.add(historyPanel, "HISTORY");

       historyPanel.loadHistoryFromFile();
       controller = new AppController(findSlangPanel, findDefinitionPanel, historyPanel); 
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        HeaderPanel header = new HeaderPanel();
        SidebarPanel sidebar = new SidebarPanel(this::navigateTo);
        DictionaryPanel dictionary = new DictionaryPanel(mainContainer);
        add(header, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(dictionary, BorderLayout.EAST);
        add(mainContainer, BorderLayout.CENTER);
    }

    public void navigateTo(String PanelName) {
        cardLayout.show(mainContainer, PanelName);
    }
}