package ua.knu.homework.hw06.task9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Task9Runner {
    public static void main(String[] args) {
        String inputPath = "students_input.txt";
        String outputPath = "students_9_10.txt";

        try {
            generateSampleFile(inputPath);

            SchoolRegistry registry = new SchoolRegistry();
            registry.loadFromFile(inputPath);

            System.out.println("~ School Registry Loaded (" + registry.getStudents().size() + " students) ~");

            System.out.println("\na) Same last names in school: " + registry.hasDuplicateLastNamesInSchool());
            System.out.println("b) Same last names in parallel grades: " + registry.hasDuplicateLastNamesInParallelGrades());
            System.out.println("v) Same last names in 9a: " + registry.hasDuplicateLastNamesInClass("9a"));
            System.out.println("v) Same last names in 10a: " + registry.hasDuplicateLastNamesInClass("10a"));

            System.out.println("\ng) Full namesakes in school: " + registry.hasFullNamesakesInSchool());
            System.out.println("g) Full namesakes in parallel grades: " + registry.hasFullNamesakesInParallelGrades());
            System.out.println("g) Full namesakes in 9a: " + registry.hasFullNamesakesInClass("9a"));

            System.out.println("\nd) Classes with more than 35 students (threshold test > 3):");
            List<String> largeClasses = registry.getClassesWithMoreThan(3);
            if (largeClasses.isEmpty()) {
                System.out.println("None found.");
            } else {
                for (String c : largeClasses) {
                    System.out.println(" - " + c);
                }
            }

            registry.exportHighSchoolGrades(outputPath);
            System.out.println("\ne) File '" + outputPath + "' generated for grades 9-10 (sorted by class).");
            System.out.println("- Exported File Content -");
            printFile(outputPath);

            System.out.println("\nzh) Class 9a roster formats:");
            List<Student> class9a = registry.getStudentsOfClass("9a");
            System.out.println("Pattern 1 (LastName FirstName):");
            for (Student s : class9a) {
                System.out.println("  " + s.formatFullName());
            }

            System.out.println("Pattern 2 (LastName I.):");
            for (Student s : class9a) {
                System.out.println("  " + s.formatLastNameInitial());
            }

            System.out.println("Pattern 3 (I.LastName):");
            for (Student s : class9a) {
                System.out.println("  " + s.formatInitialLastName());
            }

        } catch (IOException e) {
            System.err.println("File processing error: " + e.getMessage());
        }
    }

    private static void generateSampleFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Ivan Kovalenko 9a\n");
            writer.write("Olena Shevchenko 9a\n");
            writer.write("Ivan Kovalenko 9a\n");
            writer.write("Dmytro Melnyk 9a\n");
            writer.write("Anna Kovalenko 9b\n");
            writer.write("Maksym Boyko 9b\n");
            writer.write("Sofiia Bondar 10a\n");
            writer.write("Taras Shevchenko 10a\n");
            writer.write("Yaroslav Tkachenko 10b\n");
            writer.write("Alina Moroz 11a\n");
        }
    }

    private static void printFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("  " + line);
            }
        }
    }
}