package ua.knu.classwork.cw03;

public class Task6 {
    public static void main(String[] args) {
        String[] languages = {"Java", "Python", "C++", "Kotlin", "TypeScript"};

        // 1. Print array in a single line separated by commas
        System.out.println("Single line output:");
        for (int i = 0; i < languages.length; i++) {
            System.out.print(languages[i]);
            if (i < languages.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        // 2. Print each element on a new line with its array index
        System.out.println("\nIndexed elements output:");
        for (int i = 0; i < languages.length; i++) {
            System.out.printf("languages[%d] = %s%n", i, languages[i]);
        }
    }
}