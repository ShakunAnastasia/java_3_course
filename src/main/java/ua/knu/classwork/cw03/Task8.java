package ua.knu.classwork.cw03;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ROOT);

        System.out.println("Enter array length:");
        if (!scanner.hasNextInt()) {
            System.err.println("Error: Invalid length input.");
            return;
        }
        int n = scanner.nextInt();

        if (n <= 0) {
            System.err.println("Error: Array length must be positive.");
            return;
        }

        int[] arr = new int[n];
        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            if (!scanner.hasNextInt()) {
                System.err.println("Error: Insufficient elements provided.");
                return;
            }
            arr[i] = scanner.nextInt();
        }

        if (n == 1) {
            System.out.println("\nMaximum monotonic segments:");
            System.out.println(arr[0]);
            scanner.close();
            return;
        }

        List<int[]> segments = new ArrayList<>();
        int maxLength = 1;

        int i = 0;
        while (i < n - 1) {
            if (arr[i] < arr[i + 1]) {
                int start = i;
                while (i + 1 < n && arr[i] < arr[i + 1]) {
                    i++;
                }
                int end = i;
                int currentLen = end - start + 1;

                // Yield the peak element to the following decreasing segment
                // if it forms a decreasing segment of at least equal length and currentLen > 2
                if (i + 1 < n && arr[i] > arr[i + 1] && currentLen > 2) {
                    int decCount = 1;
                    int k = i;
                    while (k + 1 < n && arr[k] > arr[k + 1]) {
                        decCount++;
                        k++;
                    }
                    if (decCount >= currentLen) {
                        end = i - 1;
                    }
                }

                int len = end - start + 1;
                if (len >= 2) {
                    addSegment(segments, arr, start, len);
                    if (len > maxLength) {
                        maxLength = len;
                    }
                }
                if (end < i) {
                    i = end + 1;
                }
            } else if (arr[i] > arr[i + 1]) {
                int start = i;
                while (i + 1 < n && arr[i] > arr[i + 1]) {
                    i++;
                }
                int len = i - start + 1;
                addSegment(segments, arr, start, len);
                if (len > maxLength) {
                    maxLength = len;
                }
            } else {
                i++;
            }
        }

        System.out.println("\nMaximum monotonic segments:");
        final int targetLength = maxLength;
        segments.stream()
                .filter(seg -> seg.length == targetLength)
                .forEach(seg -> {
                    for (int j = 0; j < seg.length; j++) {
                        System.out.print(seg[j] + (j < seg.length - 1 ? " " : ""));
                    }
                    System.out.println();
                });

        scanner.close();
    }

    private static void addSegment(List<int[]> list, int[] arr, int from, int length) {
        if (length <= 1) return;
        int[] sub = new int[length];
        System.arraycopy(arr, from, sub, 0, length);
        list.add(sub);
    }
}