package ua.knu.classwork.cw02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter N for permutations:");
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        System.out.println("Lexicographic permutations:");
        int[] elements = new int[n];
        for (int i = 0; i < n; i++) elements[i] = i + 1;
        printPermutations(elements);

        System.out.println("\nEnter N and K for combinations (separated by space):");
        if (!scanner.hasNextInt()) return;
        int combN = scanner.nextInt();
        if (!scanner.hasNextInt()) return;
        int combK = scanner.nextInt();

        System.out.printf("Combinations C(%d, %d):%n", combN, combK);
        generateCombinations(1, combN, combK, new ArrayList<>());

        scanner.close();
    }

    private static void printPermutations(int[] arr) {
        Arrays.sort(arr);
        while (true) {
            for (int val : arr) System.out.print(val);
            System.out.print(" ");

            // Find next lexicographical permutation
            int i = arr.length - 2;
            while (i >= 0 && arr[i] >= arr[i + 1]) i--;
            if (i < 0) break;

            int j = arr.length - 1;
            while (arr[j] <= arr[i]) j--;

            swap(arr, i, j);
            reverse(arr, i + 1, arr.length - 1);
        }
        System.out.println();
    }

    private static void generateCombinations(int start, int n, int k, List<Integer> current) {
        if (current.size() == k) {
            for (int val : current) System.out.print(val + " ");
            System.out.println();
            return;
        }
        for (int i = start; i <= n; i++) {
            current.add(i);
            generateCombinations(i + 1, n, k, current);
            current.remove(current.size() - 1);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void reverse(int[] arr, int from, int to) {
        while (from < to) {
            swap(arr, from++, to--);
        }
    }
}
