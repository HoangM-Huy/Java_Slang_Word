import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SlangErrorCatcher {
    public static void main(String[] args) {
        // Points directly to the file without modifying it
        String fileName = "slang.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            int errorCount = 0;

            System.out.println("Scanning for errors in: " + fileName + "\n");

            while ((line = br.readLine()) != null) {
                lineNumber++;

                // Skip completely empty lines to avoid false positives
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Split the line using the backtick delimiter
                // The -1 ensures that trailing empty spaces (missing definitions) are caught
                String[] parts = line.split("`", -1);

                // Error 1: The line does not have exactly one backtick
                if (parts.length != 2) {
                    System.out.println("Line " + lineNumber + " | Error: Invalid format (Missing or extra '`') -> " + line);
                    errorCount++;
                } 
                // Error 2: The left side of the backtick is empty (Missing Slang)
                else if (parts[0].trim().isEmpty()) {
                    System.out.println("Line " + lineNumber + " | Error: Missing slang word -> " + line);
                    errorCount++;
                } 
                // Error 3: The right side of the backtick is empty (Missing Definition)
                else if (parts[1].trim().isEmpty()) {
                    System.out.println("Line " + lineNumber + " | Error: Missing definition -> " + line);
                    errorCount++;
                }
            }
            
            System.out.println("\nScan complete. Total errors found: " + errorCount);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}