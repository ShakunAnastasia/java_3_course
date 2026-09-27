package ua.knu.homework.hw01;

import java.util.Locale;
import java.util.Scanner;

public class Task17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ROOT);

        System.out.println("Enter two real numbers separated by a space in a single line:");

        if (!scanner.hasNextDouble()) {
            System.err.println("Error: First input is not a valid real number.");
            return;
        }
        double a = scanner.nextDouble();

        if (!scanner.hasNextDouble()) {
            System.err.println("Error: Second input is not a valid real number.");
            return;
        }
        double b = scanner.nextDouble();

        // 1. Calculate arithmetic mean: A = (a + b) / 2
        double arithmeticMean = (a + b) / 2.0;

        // 2. Calculate Harmonic Mean: H = 2ab / (a + b) with numerical stability check
        Double harmonicMean = null;
        double sum = a + b;
        final double EPS = 1e-12;

        if (Math.abs(a) > EPS && Math.abs(b) > EPS && Math.abs(sum) > EPS) {
            double h = (2.0 * a * b) / sum;
            if (!Double.isNaN(h) && !Double.isInfinite(h)) {
                harmonicMean = h;
            }
        }

        // 3. Formatted output in decimal and scientific notations
        System.out.println("\n~ Calculation results ~");

        // Arithmetic mean
        System.out.printf(Locale.ROOT, "Arithmetic mean (decimal):    %.6f%n", arithmeticMean);
        System.out.printf(Locale.ROOT, "Arithmetic mean (scientific): %.6e%n", arithmeticMean);

        // Harmonic mean
        if (harmonicMean != null) {
            System.out.printf(Locale.ROOT, "Harmonic mean (decimal):      %.6f%n", harmonicMean);
            System.out.printf(Locale.ROOT, "Harmonic mean (scientific):   %.6e%n", harmonicMean);
        } else {
            System.out.println("Harmonic mean: Undefined (division by zero).");
        }

        scanner.close();
    }
}
