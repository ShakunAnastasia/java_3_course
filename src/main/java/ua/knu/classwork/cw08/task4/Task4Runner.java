package ua.knu.classwork.cw08.task4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task4Runner {
    private static final Comparator<Solution> SOLUTION_COMPARATOR = Comparator
            .comparingDouble(Solution::objective)
            .thenComparingDouble(Solution::x)
            .thenComparingDouble(Solution::y);

    public static void main(String[] args) {
        Path csvFile = Paths.get("candidates_8_4.csv");
        int k = 3;
        double l = 15.0;

        try {
            createSampleCsv(csvFile);
            analyzeCandidates(csvFile, k, l);
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void analyzeCandidates(Path csvPath, int k, double l) throws IOException {
        if (k <= 0) {
            throw new IllegalArgumentException("K must be a natural number (> 0), got: " + k);
        }
        if (!Double.isFinite(l)) {
            throw new IllegalArgumentException("L must be a finite number, got: " + l);
        }
        if (!Files.exists(csvPath) || !Files.isRegularFile(csvPath)) {
            throw new IllegalArgumentException("CSV file does not exist: " + csvPath.toAbsolutePath());
        }

        List<Solution> allCandidates;
        try (Stream<String> lines = Files.lines(csvPath, StandardCharsets.UTF_8)) {
            List<String> nonEmptyLines = lines
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.toList());

            int startIndex = (!nonEmptyLines.isEmpty() && isHeader(nonEmptyLines.get(0))) ? 1 : 0;

            allCandidates = nonEmptyLines.subList(startIndex, nonEmptyLines.size()).stream()
                    .map(line -> parseSolutionLine(line, csvPath))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException("Failed to read CSV file: " + csvPath + " (" + e.getMessage() + ")", e);
        }

        List<Solution> feasible = allCandidates.stream()
                .filter(Solution::isFeasible)
                .collect(Collectors.toList());

        System.out.println("~ Optimization Candidates Analysis ~");
        System.out.println("Total records read: " + allCandidates.size());
        System.out.println("Feasible candidates (x >= 0, y >= 0, x + y <= 1): " + feasible.size());

        if (feasible.isEmpty()) {
            System.out.println("No feasible candidates found. Minimum and top-K cannot be determined.");
            return;
        }

        Solution best = feasible.stream()
                .min(SOLUTION_COMPARATOR)
                .orElseThrow();

        List<Solution> topK = feasible.stream()
                .sorted(SOLUTION_COMPARATOR)
                .limit(k)
                .collect(Collectors.toList());

        long countLeqL = feasible.stream()
                .filter(s -> s.objective() <= l)
                .count();

        Map<String, Long> grouped = feasible.stream()
                .collect(Collectors.groupingBy(Task4Runner::classifyObjective, Collectors.counting()));

        List<String> allBuckets = Arrays.asList(
                "objective < 0",
                "0 <= objective < 10",
                "10 <= objective < 100",
                "objective >= 100"
        );
        Map<String, Long> bucketCounts = new LinkedHashMap<>();
        for (String bucket : allBuckets) {
            bucketCounts.put(bucket, grouped.getOrDefault(bucket, 0L));
        }

        System.out.println("Best feasible candidate: " + best);
        System.out.println("Top " + k + " feasible candidates:");
        topK.forEach(s -> System.out.println(" - " + s));
        System.out.println("Feasible candidates with objective <= " + l + ": " + countLeqL);

        System.out.println("\n~ Objective Distribution by Intervals ~");
        bucketCounts.forEach((interval, count) -> System.out.printf("%-24s : %d%n", interval, count));
    }

    private static boolean isHeader(String line) {
        String normalized = line.replace(" ", "").toLowerCase();
        return normalized.equals("x,y,objective");
    }

    private static Solution parseSolutionLine(String line, Path file) {
        String[] parts = line.split(",", -1);
        if (parts.length != 3) {
            throw new IllegalArgumentException("Expected 3 comma-separated fields in '" + line + "' in file: " + file);
        }
        try {
            double x = Double.parseDouble(parts[0].trim());
            double y = Double.parseDouble(parts[1].trim());
            double obj = Double.parseDouble(parts[2].trim());
            if (!Double.isFinite(x) || !Double.isFinite(y) || !Double.isFinite(obj)) {
                throw new IllegalArgumentException("Non-finite value in line '" + line + "' in file: " + file);
            }
            return new Solution(x, y, obj);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numeric format in line '" + line + "' in file: " + file, e);
        }
    }

    private static String classifyObjective(Solution s) {
        double obj = s.objective();
        if (obj < 0.0) {
            return "objective < 0";
        } else if (obj < 10.0) {
            return "0 <= objective < 10";
        } else if (obj < 100.0) {
            return "10 <= objective < 100";
        } else {
            return "objective >= 100";
        }
    }

    private static void createSampleCsv(Path csvPath) throws IOException {
        List<String> rows = Arrays.asList(
                "x,y,objective",
                "0.2,0.3,5.5",
                "0.1,0.2,5.5",
                "0.0,0.5,-2.0",
                "0.4,0.4,15.0",
                "0.6,0.6,1.0",
                "-0.1,0.2,3.0",
                "0.5,0.5,120.0",
                ""
        );
        Files.write(csvPath, rows, StandardCharsets.UTF_8);
    }
}