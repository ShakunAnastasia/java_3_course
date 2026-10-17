package ua.knu.classwork.cw06.task6_7;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Task6_7Runner {
    public static void main(String[] args) {
        String binaryFileName = "doubles_6_7.bin";
        double[] sampleNumbers = {12.5, -3.25, 45.75, 10.0, -5.0, 100.5};

        try {
            writeDoublesToBinaryFile(binaryFileName, sampleNumbers);

        System.out.println("~ Reading Binary File (" + binaryFileName + ") ~");
            double totalSum = readAndSumDoubles(binaryFileName);

            System.out.printf("%nCalculated Total Sum: %.2f%n", totalSum);

        } catch (IOException e) {
            System.err.println("Binary file I/O error: " + e.getMessage());
        }
    }

    public static void writeDoublesToBinaryFile(String filePath, double[] numbers) throws IOException {
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(filePath)))) {
            for (double num : numbers) {
                out.writeDouble(num);
            }
        }
    }

    public static double readAndSumDoubles(String filePath) throws IOException {
        double sum = 0.0;
        int count = 0;

        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream(filePath)))) {
            while (true) {
                try {
                    double value = in.readDouble();
                    count++;
                    System.out.printf("Value #%d: %.2f%n", count, value);
                    sum += value;
                } catch (EOFException e) {
                    break;
                }
            }
        }
        return sum;
    }
}