package ua.knu.classwork.cw06.task_extra;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskExtraRunner {
    public static void main(String[] args) {
        String binaryFile = "numbers_extra.bin";
        String textFile = "numbers_extra.txt";

        int[] sampleIntegers = {15, 20, 35, 40, 10, 5, 80, 45};

        try {
            writeIntegersToBinary(binaryFile, sampleIntegers);

            List<Integer> readNumbers = readIntegersFromBinary(binaryFile);

            writeNumbersToTextFile(textFile, readNumbers);

            double average = calculateAverage(readNumbers);

            System.out.println("~~ Binary File Content Read Successfully ~~");
            System.out.println("Numbers: " + readNumbers);
            System.out.println("Total count: " + readNumbers.size());
            System.out.printf("Arithmetic Mean (Average): %.2f%n", average);
            System.out.println("\nText file '" + textFile + "' generated with numbers in a single line.");

        } catch (IOException e) {
            System.err.println("File I/O error: " + e.getMessage());
        }
    }

    public static void writeIntegersToBinary(String filePath, int[] numbers) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(filePath)))) {
            for (int num : numbers) {
                dos.writeInt(num);
            }
        }
    }

    public static List<Integer> readIntegersFromBinary(String filePath) throws IOException {
        List<Integer> list = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(new FileInputStream(filePath)))) {
            while (true) {
                try {
                    list.add(dis.readInt());
                } catch (EOFException e) {
                    break;
                }
            }
        }
        return list;
    }

    public static void writeNumbersToTextFile(String filePath, List<Integer> numbers) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numbers.size(); i++) {
                sb.append(numbers.get(i));
                if (i < numbers.size() - 1) {
                    sb.append(" ");
                }
            }
            writer.write(sb.toString());
        }
    }

    public static double calculateAverage(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return 0.0;
        }
        long sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.size();
    }
}