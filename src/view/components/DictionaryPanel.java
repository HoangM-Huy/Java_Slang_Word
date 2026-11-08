package view.components;

import java.awt.*;
import javax.swing.*;
import model.*;

public class DictionaryPanel extends JPanel {
    private JPanel entriesContainer;
    private JButton resetButton;

    public DictionaryPanel(JPanel contentPanel) {
        // Use BorderLayout for proper stretching of the scrollpane
        setLayout(new BorderLayout(0, 10));

        JPanel dictionaryHeader = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel headerLabel = new JLabel("Dictionary");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        dictionaryHeader.add(headerLabel);
        add(dictionaryHeader, BorderLayout.NORTH);

        // Container for slang entries
        entriesContainer = new JPanel(new GridLayout(0, 1, 5, 5));
        
        JScrollPane scrollPane = new JScrollPane(entriesContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(220, 400));

        resetButton = new JButton("Reset");
        add(scrollPane, BorderLayout.CENTER);
        add(resetButton, BorderLayout.SOUTH);

        loadEntries();
    }

    public void loadEntries() {
        entriesContainer.removeAll();

        SlangDictionary.initializeUserFile();
        SlangDictionary.loadFromFile();

        for (String slangWord : SlangDictionary.getAllEntries().keySet()) {
            String definition = SlangDictionary.getDefinition(slangWord);
            JLabel entryLabel = new JLabel(slangWord + " : " + definition);
            entriesContainer.add(entryLabel);
        }

        entriesContainer.revalidate();
        entriesContainer.repaint();
    }

    public JButton getResetButton() {
        return resetButton;
    }
}