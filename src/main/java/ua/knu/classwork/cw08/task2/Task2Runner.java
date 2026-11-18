package ua.knu.classwork.cw08.task2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task2Runner {
    public static void main(String[] args) {
        int a = 10;
        int b = 100;

        try {
            analyzePrimes(a, b);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void analyzePrimes(int a, int b) {
        if (a < 2 || b < a) {
            throw new IllegalArgumentException("Range must satisfy 2 <= a <= b, got: [" + a + ", " + b + "]");
        }

        int[] primes = IntStream.rangeClosed(a, b)
                .filter(Task2Runner::isPrime)
                .toArray();

        List<String> twinPrimes = IntStream.range(0, Math.max(0, primes.length - 1))
                .filter(i -> primes[i + 1] - primes[i] == 2)
                .mapToObj(i -> "(" + primes[i] + ", " + primes[i + 1] + ")")
                .collect(Collectors.toList());

        List<String> emirpPairs = Arrays.stream(primes)
                .mapToObj(p -> new long[]{p, reverseNumber(p)})
                .filter(pair -> pair[1] != pair[0] && isPrimeLong(pair[1]))
                .map(pair -> "(" + pair[0] + ", " + pair[1] + ")")
                .collect(Collectors.toList());

        System.out.println("~ Prime Numbers Research on [" + a + ", " + b + "] ~");
        System.out.println("Primes: " + Arrays.toString(primes));
        System.out.println("Total primes count: " + primes.length);
        System.out.println("Twin prime pairs (p2 - p1 == 2): " + twinPrimes);
        System.out.println("Reversed prime pairs (emirp): " + emirpPairs);
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        int limit = (int) Math.sqrt(n);
        return IntStream.rangeClosed(2, limit)
                .noneMatch(divisor -> n % divisor == 0);
    }

    public static boolean isPrimeLong(long n) {
        if (n < 2) {
            return false;
        }
        long limit = (long) Math.sqrt(n);
        for (long d = 2; d <= limit; d++) {
            if (n % d == 0) {
                return false;
            }
        }
        return true;
    }

    public static long reverseNumber(int n) {
        long reversed = 0;
        int temp = n;
        while (temp > 0) {
            reversed = reversed * 10 + (temp % 10);
            temp /= 10;
        }
        return reversed;
    }
}