package ua.knu.classwork.cw02;

import java.math.BigInteger;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");

        if (!scanner.hasNextLong()) {
            System.err.println("The number is not natural!");
            return;
        }
        long n = scanner.nextLong();

        if (n <= 0) {
            System.out.println("The number is not natural!");
            return;
        }

        BigInteger iterativeResult = factorialIterative(n);
        BigInteger recursiveResult = factorialRecursive(BigInteger.valueOf(n));

        System.out.println("Iterative factorial: " + iterativeResult);
        System.out.println("Recursive factorial: " + recursiveResult);

        scanner.close();
    }

    public static BigInteger factorialIterative(long n) {
        BigInteger result = BigInteger.ONE;
        for (long i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static BigInteger factorialRecursive(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return BigInteger.ONE;
        }
        return n.multiply(factorialRecursive(n.subtract(BigInteger.ONE)));
    }
}
