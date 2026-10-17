package ua.knu.classwork.cw06.task6_6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task6_6Runner {
    public static void main(String[] args) {
        String sourceFile = "file_F.txt";
        String destFile = "file_G.txt";

        try {
            createSampleFile(sourceFile);

            swapZerosAndOnes(sourceFile, destFile);

            System.out.println("~~ Source File (F) ~~");
            printFileContent(sourceFile);

            System.out.println("\n~~ Destination File (G) ~~");
            printFileContent(destFile);

        } catch (IOException e) {
            System.err.println("File processing error: " + e.getMessage());
        }
    }

    public static void swapZerosAndOnes(String inputPath, String outputPath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (!firstLine) {
                    writer.newLine();
                }

                StringBuilder sb = new StringBuilder(line.length());
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    if (ch == '0') {
                        sb.append('1');
                    } else if (ch == '1') {
                        sb.append('0');
                    } else {
                        sb.append(ch);
                    }
                }

                writer.write(sb.toString());
                firstLine = false;
            }
        }
    }

    private static void createSampleFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Binary: 1010011000\n");
            writer.write("Code 0 and code 1 in text\n");
            writer.write("0000 -> 1111, 1111 -> 0000\n");
            writer.write("Values: 2048, 1024, 512");
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