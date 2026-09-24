package ua.knu.classwork.cw02;

import java.util.*;

public class Ramanujan {
    public static void main(String[] args) {
        long n = 100000; // Default limit
        if (args.length > 0) {
            try {
                n = Long.parseLong(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid command line argument, using default: " + n);
            }
        }

        System.out.println("Searching for Ramanujan numbers <= " + n + ":");
        findRamanujanNumbers(n);

        System.out.println("\n~ Analysis of the number 87539319 ~");
        explainSpecialNumber(87539319L);
    }

    private static void findRamanujanNumbers(long limit) {
        Map<Long, List<String>> cubeSums = new HashMap<>();
        long maxCubeRoot = (long) Math.cbrt(limit);

        for (long a = 1; a <= maxCubeRoot; a++) {
            long a3 = a * a * a;
            for (long b = a; b <= maxCubeRoot; b++) {
                long sum = a3 + b * b * b;
                if (sum > limit) break;

                cubeSums.computeIfAbsent(sum, k -> new ArrayList<>())
                        .add(a + "^3 + " + b + "^3");
            }
        }

        cubeSums.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 2)
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    System.out.printf("%d = %s%n", entry.getKey(), String.join(" = ", entry.getValue()));
                });
    }

    private static void explainSpecialNumber(long number) {
        List<String> pairs = new ArrayList<>();
        long maxRoot = (long) Math.cbrt(number);

        for (long a = 1; a <= maxRoot; a++) {
            long a3 = a * a * a;
            long b3 = number - a3;
            if (b3 < a3) break;

            long b = Math.round(Math.cbrt(b3));
            if (b * b * b == b3) {
                pairs.add(a + "^3 + " + b + "^3");
            }
        }

        System.out.printf("87539319 is fascinating because it is the smallest number expressible as the sum of two cubes in 3 different ways.%n");
        System.out.println("Pairs: " + String.join(" = ", pairs));
    }
}