package view.components;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.function.Consumer;
import javax.swing.*;

public class SidebarPanel extends JPanel {
    public SidebarPanel(Consumer<String> navigationHandler) {
        setLayout(new GridLayout(8, 1, 5, 5));
        setPreferredSize(new Dimension(180, 0));

        JButton findSlang = new JButton("Search by slang word");
        JButton findDef = new JButton("Search by definition");
        JButton history = new JButton("History");
        JButton addSlang = new JButton("Add a slang");
        JButton editSlang = new JButton("Edit a slang");
        JButton randomSlang = new JButton("Random slang");
        JButton slangQuiz = new JButton("Slang word quiz");
        JButton defQuiz = new JButton("Definition quiz");

        findSlang.addActionListener(e -> navigationHandler.accept("SEARCHBYSLANGWORD"));
        findDef.addActionListener(e -> navigationHandler.accept("SEARCHBYDEFINITION"));
        history.addActionListener(e -> navigationHandler.accept("HISTORY"));
        addSlang.addActionListener(e -> navigationHandler.accept("ADDSLANG"));
        editSlang.addActionListener(e -> navigationHandler.accept("EDITSLANG"));
        randomSlang.addActionListener(e -> navigationHandler.accept("RANDOMSLANG"));
        slangQuiz.addActionListener(e -> navigationHandler.accept("SLANGQUIZ"));
        defQuiz.addActionListener(e -> navigationHandler.accept("DEFQUIZ"));

        add(findSlang);
        add(findDef);
        add(history);
        add(addSlang);
        add(editSlang);
        add(randomSlang);
        add(slangQuiz);
        add(defQuiz);
    }
}