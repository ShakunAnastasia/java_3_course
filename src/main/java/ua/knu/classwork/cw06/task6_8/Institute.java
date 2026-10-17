package ua.knu.classwork.cw06.task6_8;

import java.io.Serializable;
import java.util.Arrays;

public class Institute implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private final Faculty[] faculties;

    public Institute(String name, Faculty[] faculties) {
        this.name = name;
        this.faculties = faculties;
    }

    public String getName() {
        return name;
    }

    public Faculty[] getFaculties() {
        return faculties;
    }

    public int getTotalStudents() {
        int total = 0;
        if (faculties != null) {
            for (Faculty f : faculties) {
                total += f.getStudentsCount();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("Institute{name='%s', totalStudents=%d, faculties=%s}",
                name, getTotalStudents(), Arrays.toString(faculties));
    }
}