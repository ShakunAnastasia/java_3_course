package ua.knu.homework.hw06.task9;

import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    private int gradeYear;
    private char gradeLetter;

    public Student() {
        this("Default", "Student", 1, 'a');
    }

    public Student(String firstName, String lastName, int gradeYear, char gradeLetter) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gradeYear = gradeYear;
        this.gradeLetter = Character.toLowerCase(gradeLetter);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGradeYear() {
        return gradeYear;
    }

    public void setGradeYear(int gradeYear) {
        this.gradeYear = gradeYear;
    }

    public char getGradeLetter() {
        return gradeLetter;
    }

    public void setGradeLetter(char gradeLetter) {
        this.gradeLetter = Character.toLowerCase(gradeLetter);
    }

    public String getClassName() {
        return gradeYear + "" + gradeLetter;
    }

    public String formatFullName() {
        return lastName + " " + firstName;
    }

    public String formatLastNameInitial() {
        return lastName + " " + (firstName.isEmpty() ? "" : firstName.charAt(0) + ".");
    }

    public String formatInitialLastName() {
        return (firstName.isEmpty() ? "" : firstName.charAt(0) + ".") + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return gradeYear == student.gradeYear &&
                gradeLetter == student.gradeLetter &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, gradeYear, gradeLetter);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", firstName, lastName, getClassName());
    }
}