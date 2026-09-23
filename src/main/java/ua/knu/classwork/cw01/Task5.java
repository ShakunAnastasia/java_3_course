package ua.knu.classwork.cw01;

import java.util.Locale;
import java.util.Scanner;

public class Task5 {

    public static double evaluateHorner(double[] coefficients, double x) {
        if (coefficients == null || coefficients.length == 0) {
            throw new IllegalArgumentException("Coefficients array must not be empty.");
        }

        double result = coefficients[0];
        for (int i = 1; i < coefficients.length; i++) {
            result = result * x + coefficients[i];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ROOT);

        System.out.print("Enter polynomial degree n (integer >= 0): ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: Invalid degree input.");
            return;
        }

        int degree = scanner.nextInt();
        if (degree < 0) {
            System.out.println("Error: Degree must be non-negative.");
            return;
        }

        double[] coefficients = new double[degree + 1];
        System.out.println("Enter " + (degree + 1) + " coefficients from highest degree (a_" + degree + ") to constant (a_0):");

        for (int i = 0; i <= degree; i++) {
            coefficients[i] = scanner.nextDouble();
        }

        System.out.print("Enter evaluation point x: ");
        double x = scanner.nextDouble();

        double polynomialValue = evaluateHorner(coefficients, x);

        System.out.printf(Locale.ROOT, "%nPolynomial value P(%.4f) = %.6f%n", x, polynomialValue);
    }
}
