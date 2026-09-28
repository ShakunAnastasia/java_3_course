package ua.knu.classwork.cw03;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ROOT);

        System.out.println("Enter matrix size n (natural number):");
        if (!scanner.hasNextInt()) {
            System.err.println("Error: Size must be an integer.");
            return;
        }
        int n = scanner.nextInt();

        if (n <= 0) {
            System.err.println("Error: Matrix size must be positive.");
            return;
        }

        // Demo 1: Matrix filled via Random in range [-n, n]
        int[][] randomMatrix = new int[n][n];
        fillRandom(randomMatrix, n);
        System.out.printf("%nMatrix initialized randomly with range [-%d, %d]:%n", n, n);
        printMatrix(randomMatrix);

        // Demo 2: Matrix filled via Console input
        int[][] manualMatrix = new int[n][n];
        System.out.printf("%nEnter %d elements for manual matrix:%n", n * n);
        fillFromConsole(manualMatrix, scanner);
        System.out.println("\nManually entered matrix:");
        printMatrix(manualMatrix);

        scanner.close();
    }

    public static void fillRandom(int[][] matrix, int n) {
        Random random = new Random();
        int range = 2 * n + 1; // Generates values in [-n, n]
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(range) - n;
            }
        }
    }

    public static void fillFromConsole(int[][] matrix, Scanner scanner) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                while (!scanner.hasNextInt()) {
                    System.err.println("Invalid integer token, re-enter value:");
                    scanner.next();
                }
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%6d", val);
            }
            System.out.println();
        }
    }
}