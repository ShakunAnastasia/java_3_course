package ua.knu.homework.hw05.task8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task8Runner {
    public static void main(String[] args) {
        String sourceFile = "F.txt";
        String destFile = "G.txt";

        try {
            createSampleSourceFile(sourceFile);

            FileProcessor.prependSpaceToEachLine(sourceFile, destFile);

            System.out.println("~ Source File (F.txt) ~");
            printFileContent(sourceFile);

            System.out.println("\n~ Destination File (G.txt) ~");
            printFileContent(destFile);

        } catch (IOException e) {
            System.err.println("File I/O error: " + e.getMessage());
        }
    }

    private static void createSampleSourceFile(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("First line of text\n");
            writer.write("Second line of text\n");
            writer.write("Third line with 123 numbers\n");
            writer.write("Fourth line: final test");
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