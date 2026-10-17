package ua.knu.classwork.cw06.task6_8;

import java.io.Serializable;

public class Faculty implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private final int studentsCount;

    public Faculty(String name, int studentsCount) {
        this.name = name;
        this.studentsCount = studentsCount;
    }

    public String getName() {
        return name;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    @Override
    public String toString() {
        return String.format("Faculty{name='%s', studentsCount=%d}", name, studentsCount);
    }
}