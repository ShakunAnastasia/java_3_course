package ua.knu.homework.hw03;

public class Task15 {
    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.err.println("Error: Please provide an 11-digit UPC prefix as a command-line argument.");
            return;
        }

        String input = args[0].trim();
        if (input.length() != 11) {
            System.err.println("Error: Input must contain exactly 11 digits.");
            return;
        }

        long number;
        try {
            number = Long.parseLong(input);
            if (number < 0) {
                System.err.println("Error: Number must be non-negative.");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Input must be a valid 11-digit numeric value.");
            return;
        }

        // Extract digits d12 down to d2 from right to left using Long
        // number % 10 gives d2, then d3, ..., up to d12
        int[] d = new int[13];
        long temp = number;
        for (int i = 2; i <= 12; i++) {
            d[i] = (int) (temp % 10);
            temp /= 10;
        }

        // Formula: (d1 + d3 + d5 + d7 + d9 + d11) + 3*(d2 + d4 + d6 + d8 + d10 + d12) is multiple of 10
        int oddSum = d[3] + d[5] + d[7] + d[9] + d[11];
        int evenSum = d[2] + d[4] + d[6] + d[8] + d[10] + d[12];
        int s = oddSum + 3 * evenSum;

        int d1 = (10 - (s % 10)) % 10;

        System.out.println("Check digit (d1): " + d1);
        System.out.printf("Full 12-digit UPC: %011d%d%n", number, d1);
    }
}