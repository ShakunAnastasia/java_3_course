package ua.knu.classwork.cw05.task6;

import java.util.Scanner;

public class Task6Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter string to process:");
        String input = scanner.hasNextLine() ? scanner.nextLine() : "ph phone +5 - + cb cbb 123";

        System.out.println("\nSource string: \"" + input + "\"");
        System.out.println("1. " + transform1(input));
        System.out.println("2. " + transform2(input));
        System.out.println("3. " + transform3(input));
        System.out.println("4. " + transform4(input));
        System.out.println("5. " + transform5(input));

        scanner.close();
    }

    public static String transform1(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                continue;
            }
            if (ch == '+' || ch == '-') {
                sb.append(ch).append(ch);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static String transform2(String s) {
        return s.replaceAll("\\+(?=\\d)", "");
    }

    public static String transform3(String s) {
        return s.replaceAll("(?<=c)b", "");
    }

    public static String transform4(String s) {
        return s.replace("ph", "f").replace("PH", "F").replace("Ph", "F");
    }

    public static String transform5(String s) {
        return s.replaceAll("[ \\t]+", " ").trim();
    }
}