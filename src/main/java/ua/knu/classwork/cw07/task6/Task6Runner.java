package ua.knu.classwork.cw07.task6;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayDeque;
import java.util.Deque;

public class Task6Runner {
    public static void main(String[] args) {
        String binaryFile = "indexed_records_7_6.bin";
        String[] records = {
                "ID:101 | Student: Anastasia | Grade: 98",
                "ID:102 | Student: Bohdan | Grade: 85",
                "ID:103 | Student: Kateryna | Grade: 92",
                "ID:104 | Student: Dmytro | Grade: 78",
                "ID:105 | Student: Olena | Grade: 95"
        };

        try {
            long[] indexArray = writeRecordsAndBuildIndex(binaryFile, records);

            System.out.println("~ Stack Index Array (Byte Offsets) ~");
            for (int i = 0; i < indexArray.length; i++) {
                System.out.printf("Record #%d -> offset %d bytes%n", i, indexArray[i]);
            }

            System.out.println("\n~ Direct Random Access by Index ~");
            System.out.println("Access record [3]: " + readRecordByIndex(binaryFile, indexArray, 3));
            System.out.println("Access record [1]: " + readRecordByIndex(binaryFile, indexArray, 1));
            System.out.println("Access record [4]: " + readRecordByIndex(binaryFile, indexArray, 4));
            System.out.println("Access record [0]: " + readRecordByIndex(binaryFile, indexArray, 0));

        } catch (IOException e) {
            System.err.println("RandomAccessFile error: " + e.getMessage());
        }
    }

    public static long[] writeRecordsAndBuildIndex(String filePath, String[] records) throws IOException {
        Deque<Long> offsetStack = new ArrayDeque<>();

        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
            raf.setLength(0);
            for (String record : records) {
                offsetStack.push(raf.getFilePointer());
                raf.writeUTF(record);
            }
        }

        long[] indexArray = new long[offsetStack.size()];
        for (int i = indexArray.length - 1; i >= 0; i--) {
            indexArray[i] = offsetStack.pop();
        }
        return indexArray;
    }

    public static String readRecordByIndex(String filePath, long[] indexArray, int recordIndex) throws IOException {
        if (recordIndex < 0 || recordIndex >= indexArray.length) {
            throw new IndexOutOfBoundsException("Invalid record index: " + recordIndex);
        }

        try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
            raf.seek(indexArray[recordIndex]);
            return raf.readUTF();
        }
    }
}