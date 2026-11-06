package view.components;
import javax.swing.*;
import model.*;
import java.awt.*;
import javax.swing.border.Border;

public class DictionaryPanel extends JPanel {

    public DictionaryPanel(JPanel contentPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel DictionaryHeader = new JPanel();
        DictionaryHeader.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel headerLabel = new JLabel("Dictionary");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(Box.createRigidArea(new Dimension(0, 10)));
        DictionaryHeader.add(headerLabel);
        add(DictionaryHeader);
        loadEntries();
    }

    public void loadEntries() {
        JPanel entriesContainer = new JPanel(new GridLayout(0, 1, 5, 5));

        SlangDictionary.loadFromFile("src/data/my_slang.txt");
        for (String slangWord : SlangDictionary.getAllEntries().keySet()) {
            String definition = SlangDictionary.getDefinition(slangWord);
            //System.out.println("Slang: " + slangWord + ", Definition: " + definition);
            JLabel entryLabel = new JLabel(slangWord + " : " + definition);
            entriesContainer.add(entryLabel);
        }
        // entriesContainer.revalidate();
        // entriesContainer.repaint();
        JScrollPane scrollPane = new JScrollPane(entriesContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(200, 400));

        if (scrollPane != null) {
            remove(scrollPane);
        }
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
