package ua.knu.classwork.cw08.task1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task1Runner {
    public static void main(String[] args) {
        Path rootDir = Paths.get("measurements_8_1");
        double delta = 2.0;

        try {
            prepareSampleDirectory(rootDir);
            analyzeMeasurements(rootDir, delta);
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void analyzeMeasurements(Path rootDir, double delta) throws IOException {
        if (!Double.isFinite(delta) || delta < 0.0) {
            throw new IllegalArgumentException("Delta must be a finite non-negative number, got: " + delta);
        }
        if (!Files.exists(rootDir) || !Files.isDirectory(rootDir)) {
            throw new IllegalArgumentException("Directory does not exist: " + rootDir.toAbsolutePath());
        }

        List<Path> logFiles;
        try (Stream<Path> walkStream = Files.walk(rootDir)) {
            logFiles = walkStream
                    .filter(Files::isRegularFile)
                    .filter(Task1Runner::matchesLogPattern)
                    .sorted(Comparator.comparing(Path::toString))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException("Failed to traverse directory: " + rootDir + " (" + e.getMessage() + ")", e);
        }

        if (logFiles.isEmpty()) {
            throw new IllegalArgumentException("No files matching log*.txt found in: " + rootDir.toAbsolutePath());
        }

        System.out.println("~ Found Log Files (" + logFiles.size() + ") ~");
        logFiles.forEach(p -> System.out.println(" - " + p));

        double[] values = logFiles.stream()
                .flatMapToDouble(Task1Runner::readFileMeasurements)
                .toArray();

        if (values.length == 0) {
            throw new IllegalArgumentException("No measurements found across all matched files.");
        }

        DoubleSummaryStatistics stats = Arrays.stream(values).summaryStatistics();
        double mean = stats.getAverage();
        double min = stats.getMin();
        double max = stats.getMax();

        double[] sortedValues = Arrays.stream(values)
                .sorted()
                .toArray();

        double[] outliers = Arrays.stream(values)
                .filter(x -> Math.abs(x - mean) > delta)
                .toArray();

        System.out.println("\n~ Statistical Analysis ~");
        System.out.println("Count:" + stats.getCount());
        System.out.printf("Mean: %.4f%n", mean);
        System.out.printf("Min: %.4f%n", min);
        System.out.printf("Max: %.4f%n", max);
        System.out.println("Sorted: " + Arrays.toString(sortedValues));
        System.out.println("Outliers (|x - mean| > " + delta + "): " + Arrays.toString(outliers));

        if (Double.compare(min, max) == 0) {
            System.out.printf("Trimmed Mean (without 1 min & 1 max): %.4f%n", min);
        } else if (sortedValues.length <= 2) {
            System.out.println("Trimmed Mean: Cannot be calculated because no measurements remain after removing one min and one max.");
        } else {
            double trimmedMean = Arrays.stream(sortedValues)
                    .skip(1)
                    .limit(sortedValues.length - 2)
                    .average()
                    .orElseThrow();
            System.out.printf("Trimmed Mean (without 1 min & 1 max): %.4f%n", trimmedMean);
        }
    }

    private static boolean matchesLogPattern(Path path) {
        String fileName = path.getFileName().toString().toLowerCase();
        return fileName.startsWith("log") && fileName.endsWith(".txt");
    }

    private static java.util.stream.DoubleStream readFileMeasurements(Path file) {
        try (Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8)) {
            double[] fileValues = lines
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .mapToDouble(line -> parseFiniteDouble(line, file))
                    .toArray();
            return Arrays.stream(fileValues);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read file: " + file + " (" + e.getMessage() + ")", e);
        }
    }

    private static double parseFiniteDouble(String token, Path file) {
        try {
            double value = Double.parseDouble(token);
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Non-finite number '" + token + "' in file: " + file);
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format '" + token + "' in file: " + file, e);
        }
    }

    private static void prepareSampleDirectory(Path rootDir) throws IOException {
        Path subDir = rootDir.resolve("experiment_sub");
        Files.createDirectories(subDir);
        Files.write(rootDir.resolve("log_01.txt"), Arrays.asList("10.5", " 12.0 ", "", "11.5"), StandardCharsets.UTF_8);
        Files.write(subDir.resolve("LOG_2.TXT"), Arrays.asList("9.5", "18.0", "11.0"), StandardCharsets.UTF_8);
        Files.write(subDir.resolve("notes.txt"), Arrays.asList("999.9"), StandardCharsets.UTF_8);
    }
}