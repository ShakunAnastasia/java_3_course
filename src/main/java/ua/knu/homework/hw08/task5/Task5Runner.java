package ua.knu.homework.hw08.task5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task5Runner {
    public static void main(String[] args) {
        double[] sequence = {1, 3, 5, 4, 2, 6, 8, 7, 9, 11, 10};

        try {
            analyzeSequence(sequence);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void analyzeSequence(double[] sequence) {
        if (sequence == null || sequence.length == 0) {
            throw new IllegalArgumentException("Sequence must be a non-empty array.");
        }
        for (int i = 0; i < sequence.length; i++) {
            if (!Double.isFinite(sequence[i])) {
                throw new IllegalArgumentException("Non-finite element at index " + i + ": " + sequence[i]);
            }
        }

        int n = sequence.length;

        List<String> localMaxima = (n < 3)
                ? List.of()
                : IntStream.range(1, n - 1)
                .filter(i -> sequence[i] > sequence[i - 1] && sequence[i] > sequence[i + 1])
                .mapToObj(i -> formatElement(sequence[i]) + " (index " + i + ")")
                .collect(Collectors.toList());

        List<String> localMinima = (n < 3)
                ? List.of()
                : IntStream.range(1, n - 1)
                .filter(i -> sequence[i] < sequence[i - 1] && sequence[i] < sequence[i + 1])
                .mapToObj(i -> formatElement(sequence[i]) + " (index " + i + ")")
                .collect(Collectors.toList());

        int maxStart = 0;
        int maxLen = 1;
        int currStart = 0;
        int currLen = 1;

        for (int i = 1; i < n; i++) {
            if (sequence[i] > sequence[i - 1]) {
                currLen++;
                if (currLen > maxLen) {
                    maxLen = currLen;
                    maxStart = currStart;
                }
            } else {
                currStart = i;
                currLen = 1;
            }
        }

        int maxEnd = maxStart + maxLen - 1;
        double[] longestFragment = Arrays.copyOfRange(sequence, maxStart, maxEnd + 1);

        System.out.println("~ Numeric Sequence Analysis ~");
        System.out.println("Sequence (n = " + n + "): " + Arrays.toString(sequence));
        System.out.println("Local maxima: " + (localMaxima.isEmpty() ? "none" : localMaxima));
        System.out.println("Local minima: " + (localMinima.isEmpty() ? "none" : localMinima));
        System.out.println("Transitions (strictly increasing -> strictly decreasing): " + localMaxima.size());
        System.out.println("Transitions (strictly decreasing -> strictly increasing): " + localMinima.size());
        System.out.printf("Longest strictly increasing fragment: %s (indices %d..%d, length = %d)%n",
                Arrays.toString(longestFragment), maxStart, maxEnd, maxLen);
    }

    private static String formatElement(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}