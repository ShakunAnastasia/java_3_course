package ua.knu.classwork.cw07.task7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task7Runner {
    public static void main(String[] args) {
        String fileName = "lines_7_7.txt";

        try {
            createSampleFile(fileName);

            List<String> lines = readLinesFromFile(fileName);

            System.out.println("~ Unsorted Lines from File ~");
            printList(lines);

            Collections.sort(lines);

            System.out.println("\n~ Sorted Lines (Collections.sort) ~");
            printList(lines);

        } catch (IOException e) {
            System.err.println("File I/O error: " + e.getMessage());
        }
    }

    private static void createSampleFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Zebra\n");
            writer.write("Apple\n");
            writer.write("Mango\n");
            writer.write("Banana\n");
            writer.write("Cherry\n");
            writer.write("Apricot");
        }
    }

    private static List<String> readLinesFromFile(String filePath) throws IOException {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        }
        return list;
    }

    private static void printList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, list.get(i));
        }
    }
}