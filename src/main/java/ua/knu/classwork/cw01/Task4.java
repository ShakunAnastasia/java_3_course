package ua.knu.classwork.cw01;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Scanner;

public class Task4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ROOT);

        System.out.print("Enter a real number between 0 and 10000: ");

        if (!scanner.hasNextBigDecimal()) {
            System.out.println("Error: Invalid numeric input.");
            return;
        }

        BigDecimal number = scanner.nextBigDecimal();

        if (number.compareTo(BigDecimal.ZERO) < 0 || number.compareTo(new BigDecimal("10000")) > 0) {
            System.out.println("Error: The number must be in the range from 0 to 10000.");
            return;
        }

        BigDecimal result = number.pow(8);

        BigDecimal formattedResult = result.setScale(4, RoundingMode.HALF_UP);

        System.out.printf(Locale.ROOT, "8th power (width 20 before dot, 4 after):%n%25.4f%n", formattedResult);
    }
}