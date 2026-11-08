package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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

        for (String item : definition.split("[|\\r\\n]+")) {
            String trimmedItem = item.trim();

            if (!trimmedItem.isEmpty()) {
                definitions.add(trimmedItem);
            }
        }

        // FIX: a definition made only of separators (e.g. "|") would create an empty entry
        if (definitions.isEmpty()) {
            return;
        }

        switch (mode) {
            case NEW:
            case OVERWRITE:
                slangEntries.put(slangWord, definitions);
                break;

            case DUPLICATE:
                // FIX: merge into the existing list and skip definitions it already has
                List<String> existing = slangEntries.computeIfAbsent(slangWord, key -> new ArrayList<>());
                for (String d : definitions) {
                    if (existing.stream().noneMatch(x -> x.equalsIgnoreCase(d))) {
                        existing.add(d);
                    }
                }
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
        Path userFilePath = Paths.get(USER_FILE);
        Path originalFilePath = Paths.get(ORIGINAL_FILE);

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

    public static boolean resetDictionary() {
        Path userFilePath = Paths.get(USER_FILE);
        Path originalFilePath = Paths.get(ORIGINAL_FILE);

        if (!Files.exists(originalFilePath)) {
            System.err.println("Error: Original file not found at " + originalFilePath.toAbsolutePath());
            return false;
        }

        try {
            if (userFilePath.getParent() != null) {
                Files.createDirectories(userFilePath.getParent());
            }

            Files.copy(originalFilePath, userFilePath, StandardCopyOption.REPLACE_EXISTING);

            loadFromFile(); // loadFromFile now clears the map itself
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static SlangQuiz generateSlangQuiz() {
        List<String> keys = new ArrayList<>(slangEntries.keySet());
        if (keys.size() < 4) {
            return null;
        }

        Random random = new Random();
        String slang = keys.get(random.nextInt(keys.size()));
        String correct = getDefinition(slang);

        Set<String> options = new LinkedHashSet<>();
        options.add(correct);

        int attempts = 0;
        while (options.size() < 4 && attempts < 200) {
            String other = keys.get(random.nextInt(keys.size()));
            if (!other.equals(slang)) {
                options.add(getDefinition(other));
            }
            attempts++;
        }
        if (options.size() < 4) {
            return null;
        }

        List<String> shuffled = new ArrayList<>(options);
        Collections.shuffle(shuffled);
        return new SlangQuiz(slang, shuffled, shuffled.indexOf(correct));
    }

    public static SlangQuiz generateDefinitionQuiz() {
        List<String> keys = new ArrayList<>(slangEntries.keySet());
        if (keys.size() < 4) {
            return null;
        }

        Random random = new Random();
        String slang = keys.get(random.nextInt(keys.size()));
        List<String> defs = slangEntries.get(slang);
        if (defs.isEmpty()) {
            return null;
        }

        String definition = defs.get(random.nextInt(defs.size()));

        Set<String> options = new LinkedHashSet<>();
        options.add(slang);

        int attempts = 0;
        while (options.size() < 4 && attempts < 200) {
            String other = keys.get(random.nextInt(keys.size()));
            if (!other.equals(slang) && !hasDefinition(other, definition)) {
                options.add(other);
            }
            attempts++;
        }
        if (options.size() < 4) {
            return null;
        }

        List<String> shuffled = new ArrayList<>(options);
        Collections.shuffle(shuffled);
        return new SlangQuiz(definition, shuffled, shuffled.indexOf(slang));
    }

    private static boolean hasDefinition(String slangWord, String definition) {
        for (String d : slangEntries.get(slangWord)) {
            if (d.equalsIgnoreCase(definition)) {
                return true;
            }
        }
        return false;
    }

    public static void loadFromFile() {
        clearEntries();

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
                        addEntry(currentSlangWord, line.substring(delimiterIndex + 1).trim(), AddMode.DUPLICATE);
                    }
                } else if (currentSlangWord != null) {
                    addEntry(currentSlangWord, line.trim(), AddMode.DUPLICATE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {

            for (Map.Entry<String, List<String>> entry : slangEntries.entrySet()) {
                if (entry.getValue().isEmpty()) {
                    continue;
                }

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