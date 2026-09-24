package ua.knu.homework.hw02;

public class FivePerLine {
    public static void main(String[] args) {
        final int START = 1000;
        final int END = 2000;
        final int PER_LINE = 5;

        for (int i = START; i <= END; i++) {
            System.out.print(i);

            // Single if statement to control line breaks after every 5 numbers
            if ((i - START + 1) % PER_LINE == 0) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
    }
}