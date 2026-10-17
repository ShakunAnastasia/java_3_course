package ua.knu.classwork.cw05.task7;

import java.util.Scanner;

public class Task7Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text:");
        String input = scanner.hasNextLine() ? scanner.nextLine() : "This is a small box and a large container. Smaller items are not large.";

        String result = addVeryBeforeWords(input);

        System.out.println("\nSource text:\n" + input);
        System.out.println("\nProcessed text:\n" + result);

        scanner.close();
    }

    public static String addVeryBeforeWords(String text) {
        return text.replaceAll("(?i)\\b(small|large)\\b", "very $1");
    }
}