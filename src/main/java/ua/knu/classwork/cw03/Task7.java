package ua.knu.classwork.cw03;

import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text (press Ctrl+D or Ctrl+Z followed by Enter to finish):");

        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineCount++;

            // Characters in line plus standard newline representation ('\n')
            charCount += line.length() + 1;

            String trimmed = line.trim();
            if (!trimmed.isEmpty()) {
                String[] words = trimmed.split("\\s+");
                wordCount += words.length;
            }
        }

        System.out.println("\n--- Text Statistics ---");
        System.out.println("Lines:      " + lineCount);
        System.out.println("Words:      " + wordCount);
        System.out.println("Characters: " + charCount);

        scanner.close();
    }
}