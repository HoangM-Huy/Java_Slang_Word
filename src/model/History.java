package model;
import java.util.ArrayList;
import java.util.List;

public class History {
    private static List<String> historyList = new ArrayList<>();
    private static final String HISTORY_FILE = "src/data/history.txt";
    
    public static void addToHistory(String query) {
        if (historyList == null) {
            historyList = new ArrayList<>();
        }
        historyList.add(query);
        saveHistoryToFile();
    }

    public static List<String> getHistory() {
        return historyList;
    }

    public static void clearHistory() {
        historyList.clear();
        saveHistoryToFile();
    }

    public static void saveHistoryToFile() {
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(HISTORY_FILE))) {
            for (String entry : historyList) {
                writer.write(entry);
                writer.newLine();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadHistoryFromFile() {
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(HISTORY_FILE))) {
            historyList.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                historyList.add(line);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
