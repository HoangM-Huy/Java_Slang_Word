package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlangDictionary {
    private static Map<String, List<String>> slangEntries = new HashMap<>();
    private static final String ORIGINAL_FILE = "src/data/my_slang.txt";
    private static final String USER_FILE = "src/data/user_slang.txt";

    public enum AddMode {
        NEW,
        OVERWRITE,
        DUPLICATE
    }

    public static void addEntry(String slangWord, String definition, AddMode mode) {
        List<String> definitions = new ArrayList<>();

        for (String item : definition.split("\\|")) {
            String trimmedItem = item.trim();

            if (!trimmedItem.isEmpty()) {
                definitions.add(trimmedItem);
            }
        }

        switch (mode) {
            case NEW:
                slangEntries.put(slangWord, definitions);
                break;

            case OVERWRITE:
                slangEntries.put(slangWord, definitions);
                break;

            case DUPLICATE:
                slangEntries.computeIfAbsent(slangWord, key -> new ArrayList<>())
                            .addAll(definitions);
                break;
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

    public static boolean removeEntry(String slangWord) {
        if (slangEntries.containsKey(slangWord)) {
            slangEntries.remove(slangWord);
            return true;
        }
        return false;
    }

    public static void initializeUserFile() {
        Path userFilePath = java.nio.file.Paths.get(USER_FILE);
        Path originalFilePath = java.nio.file.Paths.get(ORIGINAL_FILE);

        try {
            if (userFilePath.getParent() != null && !Files.exists(userFilePath.getParent())) {
                Files.createDirectories(userFilePath.getParent());
            }

            boolean isUserFileMissing = !Files.exists(userFilePath);
            boolean isUserFileEmpty = Files.exists(userFilePath) && Files.size(userFilePath) == 0;

            if (isUserFileMissing || isUserFileEmpty) {
                if (Files.exists(originalFilePath)) {
                    Files.copy(originalFilePath, userFilePath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Successfully copied original slang file to user file.");
                } else {
                    System.err.println("Error: Original file not found at " + originalFilePath.toAbsolutePath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
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
                        addEntry(currentSlangWord, line.substring(delimiterIndex + 1).trim(), AddMode.NEW);
                    }
                } else if (currentSlangWord != null) {
                    addEntry(currentSlangWord, line.trim(), AddMode.NEW);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {

            for (Map.Entry<String, List<String>> entry : slangEntries.entrySet()) {
                writer.write(entry.getKey() + "`");

                writer.write(entry.getValue().get(0));
                writer.newLine();

                for (int i = 1; i < entry.getValue().size(); i++) {
                    writer.write("    " + entry.getValue().get(i));
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
    }
}
}
