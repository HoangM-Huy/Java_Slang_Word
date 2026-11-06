package model;
import java.util.ArrayList;
import java.util.List;

public class History {
    private static List<String> historyList = new ArrayList<>();
    
    public static void addToHistory(String query) {
        if (historyList == null) {
            historyList = new ArrayList<>();
        }
        historyList.add(query);
    }

    public static List<String> getHistory() {
        return historyList;
    }

    public static void clearHistory() {
        historyList.clear();
    }

    public static void saveHistoryToFile(String filename) {
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(filename))) {
            for (String entry : historyList) {
                writer.write(entry);
                writer.newLine();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadHistoryFromFile(String filename) {
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                historyList.add(line);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
