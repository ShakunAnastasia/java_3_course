package ua.knu.classwork.cw01;

import java.util.Locale;

public class Task3 {

    public static void main(String[] args) {

        double expr1 = 1.2 + 31;

        int expr2 = 45 * 54 - 11;

        int expr3 = 15 / 4;

        double expr4 = 15.0 / 4;

        int expr5 = 67 % 5;

        double expr6 = (2 * 45.1 + 3.2) / 2;

        System.out.printf(Locale.ROOT, "Expression 1 (1.2 + 31) = %.2f%n", expr1);
        System.out.printf(Locale.ROOT, "Expression 2 (45 * 54 - 11) = %d%n", expr2);
        System.out.printf(Locale.ROOT, "Expression 3 (15 / 4) = %d (integer division)%n", expr3);
        System.out.printf(Locale.ROOT, "Expression 4 (15.0 / 4) = %.2f (floating-point division)%n", expr4);
        System.out.printf(Locale.ROOT, "Expression 5 (67 %% 5) = %d%n", expr5);
        System.out.printf(Locale.ROOT, "Expression 6 ((2 * 45.1 + 3.2) / 2) = %.2f%n", expr6);
    }
}