package ua.knu.classwork.cw06.task6_8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Task6_8Runner {
    public static void main(String[] args) {
        String serializationFile = "institute_serialized.dat";

        Faculty[] faculties = {
                new Faculty("Faculty of Mechanics and Mathematics", 650),
                new Faculty("Faculty of Computer Science and Cybernetics", 920),
                new Faculty("Faculty of Physics", 480)
        };

        Institute originalInstitute = new Institute("Taras Shevchenko National University Institute", faculties);

        System.out.println("~~ Original Object Before Serialization ~~");
        System.out.println(originalInstitute);

        try {
            serializeInstitute(originalInstitute, serializationFile);
            System.out.println("\nSerialization successfully completed to " + serializationFile);

            Institute deserializedInstitute = deserializeInstitute(serializationFile);
            System.out.println("\n~~ Deserialized Object After Reading ~~");
            System.out.println(deserializedInstitute);

            boolean isDataEqual = originalInstitute.toString().equals(deserializedInstitute.toString());
            System.out.println("\nIntegrity Check (Matches Original): " + isDataEqual);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Serialization error: " + e.getMessage());
        }
    }

    public static void serializeInstitute(Institute institute, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(institute);
        }
    }

    public static Institute deserializeInstitute(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Institute) ois.readObject();
        }
    }
}