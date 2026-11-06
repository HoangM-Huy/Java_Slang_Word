package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlangDictionary {
    private static Map<String, List<String>> slangEntries = new HashMap<>();

    public static void addEntry(String slangWord, String definition) {
        List<String> definitions = slangEntries.computeIfAbsent(slangWord, key -> new ArrayList<>());
        for (String item : definition.split("\\|")) {
            String trimmedItem = item.trim();
            if (!trimmedItem.isEmpty()) {
                definitions.add(trimmedItem);
            }
        }
    }

    public static String getDefinition(String slangWord) {
        List<String> definitions = slangEntries.get(slangWord);
        return definitions == null ? null : String.join("; ", definitions);
    }

    public static String getSlangByDefinition(String definition) {
        for (Map.Entry<String, List<String>> entry : slangEntries.entrySet()) {
            for (String def : entry.getValue()) {
                if (def.equalsIgnoreCase(definition)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    public static List<String> getDefinitions(String slangWord) {
        List<String> definitions = slangEntries.get(slangWord);
        return definitions == null ? Collections.emptyList() : Collections.unmodifiableList(definitions);
    }

    public static Map<String, List<String>> getAllEntries() {
        return slangEntries;
    }

    public static void clearEntries() {
        slangEntries.clear();
    }

    public static void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String currentSlangWord = null;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                int delimiterIndex = line.indexOf('`');
                if (delimiterIndex >= 0) {
                    String slangWord = line.substring(0, delimiterIndex).trim();
                    currentSlangWord = slangWord.isEmpty() ? null : slangWord;
                    if (currentSlangWord != null) {
                        addEntry(currentSlangWord, line.substring(delimiterIndex + 1).trim());
                        //System.out.println("Load slang word: " + currentSlangWord + " with definition: " + line.substring(delimiterIndex + 1).trim());
                    }
                } else if (currentSlangWord != null) {
                    addEntry(currentSlangWord, line.trim());
                    //System.out.println("Load additional definition for slang word: " + currentSlangWord + " with definition: " + line.trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
