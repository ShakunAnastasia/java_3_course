package ua.knu.classwork.cw05.task8;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first string (or press Enter for default):");
        String firstInput = scanner.nextLine();
        if (firstInput.trim().isEmpty()) {
            firstInput = "%^80 hhhhh20&& &&nd";
        }

        System.out.println("Enter second string (or press Enter for default):");
        String secondInput = scanner.nextLine();
        if (secondInput.trim().isEmpty()) {
            secondInput = "abc50xyz--150??";
        }

        int result = addStringsWithNoise(firstInput, secondInput);

        System.out.println("\nFirst cleaned value sum: " + extractAndSumNumbers(firstInput));
        System.out.println("Second cleaned value sum: " + extractAndSumNumbers(secondInput));
        System.out.println("Final total sum (int): " + result);

        scanner.close();
    }

    public static int addStringsWithNoise(String s1, String s2) {
        return extractAndSumNumbers(s1) + extractAndSumNumbers(s2);
    }

    public static int extractAndSumNumbers(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(s);
        int sum = 0;

        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group());
        }

        return sum;
    }
}