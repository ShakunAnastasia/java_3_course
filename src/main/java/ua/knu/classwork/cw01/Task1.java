package ua.knu.classwork.cw01;

public class Task1 {

    private int defaultInt;
    private char defaultChar;
    private String defaultString;

    public static void main(String[] args) {
        System.out.println("hello, world");

        Task1 instance = new Task1();

        System.out.println("Default int value: " + instance.defaultInt);
        System.out.println("Default char value (Unicode code): " + (int) instance.defaultChar);
        System.out.println("Default String value: " + instance.defaultString);
    }
}