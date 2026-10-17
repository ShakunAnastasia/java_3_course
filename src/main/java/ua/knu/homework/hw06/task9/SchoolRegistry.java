package ua.knu.homework.hw06.task9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchoolRegistry {
    private final List<Student> students = new ArrayList<>();

    public void loadFromFile(String filePath) throws IOException {
        students.clear();
        Pattern pattern = Pattern.compile("^(\\S+)\\s+(\\S+)\\s+(\\d+)([a-zA-Zа-яА-Я])$");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    String firstName = matcher.group(1);
                    String lastName = matcher.group(2);
                    int year = Integer.parseInt(matcher.group(3));
                    char letter = matcher.group(4).charAt(0);
                    students.add(new Student(firstName, lastName, year, letter));
                }
            }
        }
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public boolean hasDuplicateLastNamesInSchool() {
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).getLastName().equalsIgnoreCase(students.get(j).getLastName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasDuplicateLastNamesInParallelGrades() {
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                Student s1 = students.get(i);
                Student s2 = students.get(j);
                if (s1.getGradeYear() == s2.getGradeYear() &&
                        s1.getGradeLetter() != s2.getGradeLetter() &&
                        s1.getLastName().equalsIgnoreCase(s2.getLastName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasDuplicateLastNamesInClass(String className) {
        List<Student> classList = getStudentsOfClass(className);
        for (int i = 0; i < classList.size(); i++) {
            for (int j = i + 1; j < classList.size(); j++) {
                if (classList.get(i).getLastName().equalsIgnoreCase(classList.get(j).getLastName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasFullNamesakesInSchool() {
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                Student s1 = students.get(i);
                Student s2 = students.get(j);
                if (s1.getLastName().equalsIgnoreCase(s2.getLastName()) &&
                        s1.getFirstName().equalsIgnoreCase(s2.getFirstName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasFullNamesakesInParallelGrades() {
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                Student s1 = students.get(i);
                Student s2 = students.get(j);
                if (s1.getGradeYear() == s2.getGradeYear() &&
                        s1.getGradeLetter() != s2.getGradeLetter() &&
                        s1.getLastName().equalsIgnoreCase(s2.getLastName()) &&
                        s1.getFirstName().equalsIgnoreCase(s2.getFirstName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasFullNamesakesInClass(String className) {
        List<Student> classList = getStudentsOfClass(className);
        for (int i = 0; i < classList.size(); i++) {
            for (int j = i + 1; j < classList.size(); j++) {
                Student s1 = classList.get(i);
                Student s2 = classList.get(j);
                if (s1.getLastName().equalsIgnoreCase(s2.getLastName()) &&
                        s1.getFirstName().equalsIgnoreCase(s2.getFirstName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> getClassesWithMoreThan(int threshold) {
        Map<String, Integer> counts = new HashMap<>();
        for (Student s : students) {
            counts.put(s.getClassName(), counts.getOrDefault(s.getClassName(), 0) + 1);
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > threshold) {
                result.add(entry.getKey() + " (" + entry.getValue() + " students)");
            }
        }
        return result;
    }

    public void exportHighSchoolGrades(String outputPath) throws IOException {
        List<Student> filtered = new ArrayList<>();
        for (Student s : students) {
            if (s.getGradeYear() == 9 || s.getGradeYear() == 10) {
                filtered.add(s);
            }
        }

        filtered.sort(Comparator.comparingInt(Student::getGradeYear)
                .thenComparing(Student::getGradeLetter)
                .thenComparing(Student::getLastName)
                .thenComparing(Student::getFirstName));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (Student s : filtered) {
                writer.write(s.toString());
                writer.newLine();
            }
        }
    }

    public List<Student> getStudentsOfClass(String className) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getClassName().equalsIgnoreCase(className)) {
                result.add(s);
            }
        }
        return result;
    }
}