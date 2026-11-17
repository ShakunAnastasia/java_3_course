package ua.knu.classwork.cw07.task10;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Task10Runner {
    public static void main(String[] args) {
        String outputFile = "lines_through_points_7_10.txt";

        List<Point> points = Arrays.asList(
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(0, 2),
                new Point(2, 0),
                new Point(0, 4)
        );

        try {
            Map<Line, Set<Point>> linesMap = findAllLines(points);
            writeLinesToFile(outputFile, linesMap);

            System.out.println("~ Points on Plane (" + points.size() + ") ~");
            System.out.println(points);

            System.out.println("\n~ Lines Passing Through >1 Points (Saved to " + outputFile + ") ~");
            for (Map.Entry<Line, Set<Point>> entry : linesMap.entrySet()) {
                if (entry.getValue().size() > 1) {
                    System.out.printf("Line: %-18s | Points count: %d | Points: %s%n",
                            entry.getKey(), entry.getValue().size(), entry.getValue());
                }
            }
        } catch (IOException e) {
            System.err.println("File writing error: " + e.getMessage());
        }
    }

    public static Map<Line, Set<Point>> findAllLines(List<Point> points) {
        Map<Line, Set<Point>> map = new HashMap<>();

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                if (!p1.equals(p2)) {
                    Line line = new Line(p1, p2);
                    map.computeIfAbsent(line, k -> new HashSet<>()).add(p1);
                    map.get(line).add(p2);
                }
            }
        }
        return map;
    }

    public static void writeLinesToFile(String filePath, Map<Line, Set<Point>> linesMap) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<Line, Set<Point>> entry : linesMap.entrySet()) {
                if (entry.getValue().size() > 1) {
                    writer.write(String.format("Line: %s | Points count: %d | Points: %s",
                            entry.getKey(), entry.getValue().size(), entry.getValue()));
                    writer.newLine();
                }
            }
        }
    }
}