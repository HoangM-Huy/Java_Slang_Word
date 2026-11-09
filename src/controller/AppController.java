package controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.SlangDictionary;
import model.SlangQuiz;
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
    private RandomSlangPanel randomSlangPanel;
    private SlangQuizPanel slangQuizPanel;
    private DefinitionQuizPanel definitionQuizPanel;

    private SlangQuiz currentSlangQuiz;
    private int slangCorrect = 0;
    private int slangTotal = 0;

    private SlangQuiz currentDefinitionQuiz;
    private int definitionCorrect = 0;
    private int definitionTotal = 0;

    public AppController(
        FindSlangPanel findSlangPanel, 
        FindDefinitionPanel findDefinitionPanel, 
        HistoryPanel historyPanel, 
        AddSlangPanel addSlangPanel, 
        DictionaryPanel dictionaryPanel, 
        EditSlangPanel editSlangPanel, 
        DeleteSlangPanel deleteSlangPanel, 
        RandomSlangPanel randomSlangPanel, 
        SlangQuizPanel slangQuizPanel,
        DefinitionQuizPanel definitionQuizPanel) 
    {
        this.findSlangPanel = findSlangPanel;
        this.findDefinitionPanel = findDefinitionPanel;
        this.historyPanel = historyPanel;
        this.addSlangPanel = addSlangPanel;
        this.dictionaryPanel = dictionaryPanel;
        this.editSlangPanel = editSlangPanel;
        this.deleteSlangPanel = deleteSlangPanel;
        this.randomSlangPanel = randomSlangPanel;
        this.slangQuizPanel = slangQuizPanel;
        this.definitionQuizPanel = definitionQuizPanel;

        initializeListeners();
    }

    private void initializeListeners() {
        findSlangPanel.getSearchButton().addActionListener(e -> handleFindSlang());
        findDefinitionPanel.getSearchButton().addActionListener(e -> handleFindDefinition());
        historyPanel.setDeleteHistoryListener(e -> clearHistory());
        addSlangPanel.addAddButtonListener(e -> handleAddSlang(addSlangPanel.getSlangInput().getText(), addSlangPanel.getDefinitionInput().getText()));
        dictionaryPanel.getResetButton().addActionListener(e -> handleResetDictionary());
        editSlangPanel.getFetchButton().addActionListener(e -> handleFetchSlang());
        editSlangPanel.getUpdateButton().addActionListener(e -> handleUpdateSlang());
        deleteSlangPanel.getDeleteButton().addActionListener(e -> handleDeleteSlang());
        randomSlangPanel.getRandomButton().addActionListener(e -> handleRandomSlang());
        

        slangQuizPanel.getNextButton().addActionListener(e -> handleNewQuiz());
        JButton[] answers = slangQuizPanel.getAnswerButtons();
        for (int i = 0; i < answers.length; i++) {
            final int index = i;
            answers[i].addActionListener(e -> handleQuizAnswer(index));
        }

        definitionQuizPanel.getNextButton().addActionListener(e -> handleNewDefinitionQuiz());
        JButton[] defAnswers = definitionQuizPanel.getAnswerButtons();
        for (int i = 0; i < defAnswers.length; i++) {
            final int index = i;
            defAnswers[i].addActionListener(e -> handleReverseQuizAnswer(index));
        }
    }

    private void handleFindSlang() {
        String slangWord = findSlangPanel.getSlangInput().getText().toUpperCase().trim();
        if (!slangWord.isEmpty()) {
            List<String> definitions = SlangDictionary.getDefinitions(slangWord);
            if (!definitions.isEmpty()) {
                findSlangPanel.getResultArea().setText(String.join("\n", definitions));
                addHistory(slangWord + " : " + String.join("; ", definitions));
            } else {
                findSlangPanel.getResultArea().setText("No definition found for: " + slangWord);
            }
        }
    }

    private void handleFindDefinition() {
        String definition = findDefinitionPanel.getDefinitionInput().getText().trim();
        if (definition.isEmpty()) return;

        List<String> slangWords = SlangDictionary.getSlangByDefinition(definition);
        if (slangWords.isEmpty()) {
            findDefinitionPanel.setSlangOutput("No slang word found.");
            return;
        }

        findDefinitionPanel.setSlangOutput(String.join("\n", slangWords));

        // History: keep the line short, since "cool" alone has 56 words
        String shown = String.join(", ", slangWords.subList(0, Math.min(5, slangWords.size())));
        if (slangWords.size() > 5) shown += " (+" + (slangWords.size() - 5) + " more)";
        addHistory(shown + " : " + definition);
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

        String currentDef = String.join("\n", SlangDictionary.getDefinitions(slang));
        if (!currentDef.isEmpty()) {
            editSlangPanel.getDefinitionInput().setText(currentDef);
        } else {
            JOptionPane.showMessageDialog(editSlangPanel, "Slang word '" + slang + "' not found!", "Not Found", JOptionPane.ERROR_MESSAGE);
            editSlangPanel.getDefinitionInput().setText("");
        }
    }

    private void handleResetDictionary() {
        int confirm = JOptionPane.showConfirmDialog(
            dictionaryPanel,
            "This will discard all your changes and restore the original dictionary. Continue?",
            "Reset Dictionary",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        if (confirm != JOptionPane.YES_OPTION) return;

        if (SlangDictionary.resetDictionary()) {
            dictionaryPanel.loadEntries();
            JOptionPane.showMessageDialog(dictionaryPanel, "Dictionary restored to original.");
        } else {
            JOptionPane.showMessageDialog(dictionaryPanel, "Reset failed. Original file not found.", "Error", JOptionPane.ERROR_MESSAGE);
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
        String slang = deleteSlangPanel.getInputSlang().getText().toUpperCase().trim();

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

    private void handleRandomSlang() {
        List<String> keys = new ArrayList<>(SlangDictionary.getAllEntries().keySet());
        if (keys.isEmpty()) {
            randomSlangPanel.setRandomSlang("");
            randomSlangPanel.setRandomDef("The dictionary is empty.");
            return;
        }

        String randomKey = keys.get(new Random().nextInt(keys.size()));
        randomSlangPanel.setRandomSlang(randomKey);
        randomSlangPanel.setRandomDef(String.join("\n", SlangDictionary.getDefinitions(randomKey)));
    }

    private void handleNewQuiz() {
        currentSlangQuiz = SlangDictionary.generateSlangQuiz();
        if (currentSlangQuiz == null) {
            slangQuizPanel.showMessage("Need at least 4 slang words with different definitions.");
            return;
        }
        slangQuizPanel.showQuestion(currentSlangQuiz.getPrompt(), currentSlangQuiz.getOptions());
    }

    private void handleQuizAnswer(int chosen) {
        if (currentSlangQuiz == null) return;
        int correct = currentSlangQuiz.getCorrectAnswer();
        slangTotal++;
        if (chosen == correct) slangCorrect++;
        slangQuizPanel.showResult(chosen, correct);
        slangQuizPanel.setScore(slangCorrect, slangTotal);
    }

    private void handleNewDefinitionQuiz() {
        currentDefinitionQuiz = SlangDictionary.generateDefinitionQuiz();
        if (currentDefinitionQuiz == null) {
            definitionQuizPanel.showMessage("Need at least 4 slang words with different definitions.");
            return;
        }
        definitionQuizPanel.showQuestion(currentDefinitionQuiz.getPrompt(), currentDefinitionQuiz.getOptions());
    }

    private void handleReverseQuizAnswer(int chosen) {
        if (currentDefinitionQuiz == null) return;
        int correct = currentDefinitionQuiz.getCorrectAnswer();
        definitionTotal++;
        if (chosen == correct) definitionCorrect++;
        definitionQuizPanel.showResult(chosen, correct);
        definitionQuizPanel.setScore(definitionCorrect, definitionTotal);
    }
}
