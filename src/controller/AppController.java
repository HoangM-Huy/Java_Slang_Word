package controller;
import view.panels.*;

public class AppController {
    private FindSlangPanel findSlangPanel;
    private FindDefinitionPanel findDefinitionPanel;

    public AppController(
        FindSlangPanel findSlangPanel, 
        FindDefinitionPanel findDefinitionPanel) 
    {
        this.findSlangPanel = findSlangPanel;
        this.findDefinitionPanel = findDefinitionPanel;
        initializeListeners();
    }

    private void initializeListeners() {
        findSlangPanel.getSearchButton().addActionListener(e -> handleFindSlang());
        findDefinitionPanel.getSearchButton().addActionListener(e -> handleFindDefinition());
    }

    private void handleFindSlang() {
        String slangWord = findSlangPanel.getSlangInput().getText().toUpperCase().trim();
        if (!slangWord.isEmpty()) {
            String definition = model.SlangDictionary.getDefinition(slangWord);
            if (definition != null) {
                findSlangPanel.getResultArea().setText(definition);
            } else {
                findSlangPanel.getResultArea().setText("No definition found for: " + slangWord);
            }
        }
    }

    private void handleFindDefinition() {
        String definition = findDefinitionPanel.getDefinitionInput().getText().trim();
        if (!definition.isEmpty()) {
            String slangWord = model.SlangDictionary.getSlangByDefinition(definition);
            if (slangWord != null) {
                findDefinitionPanel.setSlangOutput(slangWord);
            } else {
                findDefinitionPanel.setSlangOutput("No slang word found for the given definition.");
            }
        }
    }
}
