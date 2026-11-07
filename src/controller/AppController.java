package controller;
import javax.swing.JOptionPane;
import model.SlangDictionary;
import view.components.*;
import view.panels.*;

public class AppController {
    private FindSlangPanel findSlangPanel;
    private FindDefinitionPanel findDefinitionPanel;
    private HistoryPanel historyPanel;
    private AddSlangPanel addSlangPanel;
    private DictionaryPanel dictionaryPanel;
    private EditSlangPanel editSlangPanel;
    private DeleteSlangPanel deleteSlangPanel;

    public AppController(
        FindSlangPanel findSlangPanel, 
        FindDefinitionPanel findDefinitionPanel, 
        HistoryPanel historyPanel, 
        AddSlangPanel addSlangPanel, 
        DictionaryPanel dictionaryPanel, 
        EditSlangPanel editSlangPanel, 
        DeleteSlangPanel deleteSlangPanel) 
    {
        this.findSlangPanel = findSlangPanel;
        this.findDefinitionPanel = findDefinitionPanel;
        this.historyPanel = historyPanel;
        this.addSlangPanel = addSlangPanel;
        this.dictionaryPanel = dictionaryPanel;
        this.editSlangPanel = editSlangPanel;
        this.deleteSlangPanel = deleteSlangPanel;

        initializeListeners();
    }

    private void initializeListeners() {
        findSlangPanel.getSearchButton().addActionListener(e -> handleFindSlang());
        findDefinitionPanel.getSearchButton().addActionListener(e -> handleFindDefinition());
        historyPanel.setDeleteHistoryListener(e -> clearHistory());
        addSlangPanel.addAddButtonListener(e -> handleAddSlang(addSlangPanel.getSlangInput().getText(), addSlangPanel.getDefinitionInput().getText()));
        editSlangPanel.getFetchButton().addActionListener(e -> handleFetchSlang());
        editSlangPanel.getUpdateButton().addActionListener(e -> handleUpdateSlang());
        deleteSlangPanel.getDeleteButton().addActionListener(e -> handleDeleteSlang());
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

    private void handleAddSlang(String slangWord, String definition) {
        slangWord = slangWord.trim().toUpperCase();
        definition = definition.trim();

        if (slangWord.isEmpty() || definition.isEmpty()) {
            JOptionPane.showMessageDialog(
                null,
                "Please enter both a slang word and its definition."
            );
            return;
        }

        if (model.SlangDictionary.getDefinitions(slangWord).isEmpty()) {
            model.SlangDictionary.addEntry(
                slangWord,
                definition,
                model.SlangDictionary.AddMode.NEW
            );

        } else {
            Object[] options = {"Overwrite", "Add Definition", "Cancel"};

            int choice = JOptionPane.showOptionDialog(
                null,
                "The slang word '" + slangWord + "' already exists.\n"
                    + "What would you like to do?",
                "Duplicate Slang Word",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[2]
            );

            switch (choice) {
                case 0:
                    model.SlangDictionary.addEntry(
                        slangWord,
                        definition,
                        model.SlangDictionary.AddMode.OVERWRITE
                    );
                    break;

                case 1:
                    model.SlangDictionary.addEntry(
                        slangWord,
                        definition,
                        model.SlangDictionary.AddMode.DUPLICATE
                    );
                    break;

                default:
                    return;
            }
        }

        model.SlangDictionary.saveToFile();
        dictionaryPanel.loadEntries();

        JOptionPane.showMessageDialog(
            null,
            "Slang word added successfully!"
        );
    }

    private void handleFetchSlang() {
        String slang = editSlangPanel.getSlangInput().getText().toUpperCase().trim();
        if (slang.isEmpty()) {
            JOptionPane.showMessageDialog(editSlangPanel, "Enter a slang word.", "Input warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String currentDef = SlangDictionary.getDefinition(slang);
        if (currentDef != null) {
            editSlangPanel.getDefinitionInput().setText(currentDef);
        } else {
            JOptionPane.showMessageDialog(editSlangPanel, "Slang word '" + slang + "' not found!", "Not Found", JOptionPane.ERROR_MESSAGE);
            editSlangPanel.getDefinitionInput().setText("");
        }
    }

    private void handleUpdateSlang() {
        String slang = editSlangPanel.getSlangInput().getText().toUpperCase().trim();
        String newDefinition = editSlangPanel.getDefinitionInput().getText().trim();

        if (slang.isEmpty() || newDefinition.isEmpty()) {
            JOptionPane.showMessageDialog(editSlangPanel, "Both slang word and new definition are required.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!SlangDictionary.getAllEntries().containsKey(slang)) {
            JOptionPane.showMessageDialog(editSlangPanel, "Cannot update. Slang word does not exist in dictionary.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SlangDictionary.addEntry(slang, newDefinition, SlangDictionary.AddMode.OVERWRITE);
        SlangDictionary.saveToFile();
        dictionaryPanel.loadEntries();

        JOptionPane.showMessageDialog(editSlangPanel, "Slang word '" + slang + "' updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleDeleteSlang() {
        String slang = deleteSlangPanel.getInputSlang().getText().trim();

        if (slang.isEmpty()) {
            JOptionPane.showMessageDialog(deleteSlangPanel, "Enter a slang word.", "Input Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!SlangDictionary.getAllEntries().containsKey(slang)) {
            JOptionPane.showMessageDialog(deleteSlangPanel, "Slang word '" + slang + "' does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            deleteSlangPanel,
            "Are you sure you want to delete '" + slang + "'?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            SlangDictionary.removeEntry(slang);
            SlangDictionary.saveToFile();

            dictionaryPanel.loadEntries();
            deleteSlangPanel.getInputSlang().setText("");

            JOptionPane.showMessageDialog(deleteSlangPanel, "Slang word '" + slang + "' deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
