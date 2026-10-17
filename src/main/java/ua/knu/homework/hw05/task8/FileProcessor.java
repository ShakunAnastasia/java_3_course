package ua.knu.homework.hw05.task8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {
    public static void prependSpaceToEachLine(String sourcePath, String destinationPath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourcePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destinationPath))) {

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (!firstLine) {
                    writer.newLine();
                }
                writer.write(" " + line);
                firstLine = false;
            }
        }
    }
}