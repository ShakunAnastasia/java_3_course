package ua.knu.homework.hw03;

import java.util.Locale;
import java.util.Scanner;

public class Task14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ROOT);

        System.out.println("Enter number of items N and max capacity W:");
        if (!scanner.hasNextInt()) {
            System.err.println("Error: N must be an integer.");
            return;
        }
        int n = scanner.nextInt();

        if (!scanner.hasNextInt()) {
            System.err.println("Error: W must be an integer.");
            return;
        }
        int w = scanner.nextInt();

        if (n < 0 || w < 0) {
            System.err.println("Error: N and W cannot be negative.");
            return;
        }

        int[] weights = new int[n];
        System.out.printf("Enter %d item weights:%n", n);
        for (int i = 0; i < n; i++) {
            if (!scanner.hasNextInt()) {
                System.err.println("Error: Insufficient weights provided.");
                return;
            }
            weights[i] = scanner.nextInt();
            if (weights[i] < 0) {
                System.err.println("Error: Weight cannot be negative.");
                return;
            }
        }

        int maxWeight = knapsack(weights, 0, w);
        System.out.println("Maximum total weight: " + maxWeight);

        scanner.close();
    }

    public static int knapsack(int[] weights, int index, int remainingCapacity) {
        if (index == weights.length || remainingCapacity <= 0) {
            return 0;
        }

        // Branch 1: Skip item
        int withoutCurrent = knapsack(weights, index + 1, remainingCapacity);

        // Branch 2: Take item
        int withCurrent = 0;
        if (weights[index] <= remainingCapacity) {
            withCurrent = weights[index] + knapsack(weights, index + 1, remainingCapacity - weights[index]);
        }

        return Math.max(withoutCurrent, withCurrent);
    }
}