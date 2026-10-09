package ua.knu.classwork.cw04.task11;

import java.util.List;

public class Task11Runner {
    public static void main(String[] args) {
        List<Publication> publications = List.of(
                new Directory("Kyiv City Phone Directory", "Telecom Editorial", 450, "Telecommunications"),
                new Directory("Java API Reference Guide", "Oracle Team", 820, "Computer Science"),
                new Encyclopedia("Encyclopaedia Britannica", "Britannica Editors", 1250, 1),
                new Encyclopedia("World History Encyclopedia", "Academic Press", 980, 4)
        );

        System.out.println("~~ Library Publications Collection ~~");
        for (Publication pub : publications) {
            pub.printDetails();
        }
    }
}