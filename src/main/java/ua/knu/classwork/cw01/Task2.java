package ua.knu.classwork.cw01;

import java.util.Locale;

public class Task2 {

    public static void main(String[] args) {
        System.out.println("~ First 3 Arguments ~");
        if (args.length >= 3) {
            System.out.println("Argument 1: " + args[0]);
            System.out.println("Argument 2: " + args[1]);
            System.out.println("Argument 3: " + args[2]);
        } else {
            System.out.println("Warning: Fewer than 3 arguments provided (received: " + args.length + ")");
            for (int i = 0; i < args.length; i++) {
                System.out.println("Argument " + (i + 1) + ": " + args[i]);
            }
        }

        double sum = 0.0;
        int realNumberCount = 0;

        for (String arg : args) {
            try {
                double value = Double.parseDouble(arg);
                sum += value;
                realNumberCount++;
            } catch (NumberFormatException ignored) {
            }
        }

        System.out.println("\n~ Numerical Analysis Results ~");
        System.out.println("Total real numbers parsed: " + realNumberCount);
        System.out.printf(Locale.ROOT, "Sum of real numbers: %f%n", sum);
    }
}