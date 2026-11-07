package controller;
import view.panels.*;

public class AppController {
    private FindSlangPanel findSlangPanel;
    private FindDefinitionPanel findDefinitionPanel;
    private HistoryPanel historyPanel;

    public AppController(
        FindSlangPanel findSlangPanel, 
        FindDefinitionPanel findDefinitionPanel, 
        HistoryPanel historyPanel) 
    {
        this.findSlangPanel = findSlangPanel;
        this.findDefinitionPanel = findDefinitionPanel;
        this.historyPanel = historyPanel;
        initializeListeners();
    }

    private void initializeListeners() {
        findSlangPanel.getSearchButton().addActionListener(e -> handleFindSlang());
        findDefinitionPanel.getSearchButton().addActionListener(e -> handleFindDefinition());
        historyPanel.setDeleteHistoryListener(e -> clearHistory());
    }

    private void handleFindSlang() {
        String slangWord = findSlangPanel.getSlangInput().getText().toUpperCase().trim();
        if (!slangWord.isEmpty()) {
            String definition = model.SlangDictionary.getDefinition(slangWord);
            if (definition != null) {
                findSlangPanel.getResultArea().setText(definition);
                addHistory(slangWord + " : " + definition);
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
                addHistory(slangWord + " : " + definition);
            } else {
                findDefinitionPanel.setSlangOutput("No slang word found.");
            }
        }
    }

    private void addHistory(String query) {
        model.History.addToHistory(query);
        historyPanel.addHistory(query);
    }

    private void clearHistory() {
        model.History.clearHistory();
        historyPanel.setHistory(new String[0]);
    }
}
