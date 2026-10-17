package ua.knu.classwork.cw06.task5_9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task5_9Runner {
    public static void main(String[] args) {
        String inputFile = "source_5_9.txt";
        String outputFile = "cleaned_5_9.txt";

        try {
            createSampleFile(inputFile);

            int wordCount = countWordsInFile(inputFile);
            System.out.println("~ Source File Content ~");
            printFileContent(inputFile);
            System.out.println("\nTotal words count: " + wordCount);

            cleanFile(inputFile, outputFile);

            System.out.println("\n~ Cleaned File Content ~");
            printFileContent(outputFile);

        } catch (IOException e) {
            System.err.println("File processing error: " + e.getMessage());
        }
    }

    public static int countWordsInFile(String filePath) throws IOException {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] words = line.split("\\s+");
                    count += words.length;
                }
            }
        }
        return count;
    }

    public static void cleanFile(String inputPath, String outputPath) throws IOException {
        List<String> processedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    processedLines.add("");
                    continue;
                }

                String[] words = line.split("\\s+");
                StringBuilder sb = new StringBuilder();
                for (String word : words) {
                    if (word.length() > 1) {
                        if (sb.length() > 0) {
                            sb.append(" ");
                        }
                        sb.append(word);
                    }
                }
                processedLines.add(sb.toString());
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (int i = 0; i < processedLines.size(); i++) {
                writer.write(processedLines.get(i));
                if (i < processedLines.size() - 1) {
                    writer.newLine();
                }
            }
        }
    }

    private static void createSampleFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("  Java   is a   great  programming   language \n");
            writer.write("I    love    coding   in   it  every   day\n");
            writer.write("   A    b    big   word   c   end   ");
        }
    }

    private static void printFileContent(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[" + line + "]");
            }
        }
    }
}