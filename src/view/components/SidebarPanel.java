package view.components;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.function.Consumer;
import javax.swing.*;

public class SidebarPanel extends JPanel {
    public SidebarPanel(Consumer<String> navigationHandler) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton findSlang = new JButton("Search by slang word");
        JButton findDef = new JButton("Search by definition");
        JButton history = new JButton("History");
        JButton addSlang = new JButton("Add a slang");
        JButton editSlang = new JButton("Edit a slang");
        JButton deleteSlang = new JButton("Delete a slang");
        JButton randomSlang = new JButton("Random slang");
        JButton slangQuiz = new JButton("Slang word quiz");
        JButton defQuiz = new JButton("Definition quiz");

        findSlang.addActionListener(e -> navigationHandler.accept("FINDSLANG"));
        findDef.addActionListener(e -> navigationHandler.accept("FINDDEF"));
        history.addActionListener(e -> navigationHandler.accept("HISTORY"));
        addSlang.addActionListener(e -> navigationHandler.accept("ADDSLANG"));
        editSlang.addActionListener(e -> navigationHandler.accept("EDITSLANG"));
        deleteSlang.addActionListener(e -> navigationHandler.accept("DELETESLANG"));
        randomSlang.addActionListener(e -> navigationHandler.accept("RANDOMSLANG"));
        slangQuiz.addActionListener(e -> navigationHandler.accept("SLANGQUIZ"));
        defQuiz.addActionListener(e -> navigationHandler.accept("DEFQUIZ"));

        JPanel buttonContainer = new JPanel(new GridLayout(9, 1, 5, 5));
        buttonContainer.setPreferredSize(new Dimension(180, 0));
        buttonContainer.add(findSlang);
        buttonContainer.add(findDef);
        buttonContainer.add(history);
        buttonContainer.add(addSlang);
        buttonContainer.add(editSlang);
        buttonContainer.add(deleteSlang);
        buttonContainer.add(randomSlang);
        buttonContainer.add(slangQuiz);
        buttonContainer.add(defQuiz);

        JScrollPane scrollPane = new JScrollPane(buttonContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }
}