package ua.knu.classwork.cw02;

import java.util.Locale;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ROOT);

        System.out.println("Enter number, bit index (1-32), and bit value (0 or 1) separated by spaces:");
        if (!scanner.hasNextInt()) {
            System.err.println("Error: Invalid number input.");
            return;
        }
        int number = scanner.nextInt();

        if (!scanner.hasNextInt()) {
            System.err.println("Error: Invalid bit index input.");
            return;
        }
        int bitPosition = scanner.nextInt();

        if (!scanner.hasNextInt()) {
            System.err.println("Error: Invalid bit value input.");
            return;
        }
        int bitValue = scanner.nextInt();

        int shift = (bitPosition >= 1 && bitPosition <= 32) ? bitPosition - 1 : bitPosition;

        if (shift < 0 || shift > 31) {
            System.err.println("Error: Bit position out of range (must be 1-32).");
            return;
        }
        if (bitValue != 0 && bitValue != 1) {
            System.err.println("Error: Bit value must be either 0 or 1.");
            return;
        }

        int modifiedNumber;
        if (bitValue == 1) {
            modifiedNumber = number | (1 << shift);
        } else {
            modifiedNumber = number & ~(1 << shift);
        }

        String hexString = "0x" + Integer.toHexString(modifiedNumber).toUpperCase();
        String binaryString = Integer.toBinaryString(modifiedNumber);
        System.out.printf("%d %s %s%n", modifiedNumber, hexString, binaryString);

        scanner.close();
    }
}