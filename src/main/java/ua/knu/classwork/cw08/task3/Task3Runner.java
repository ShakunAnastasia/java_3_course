package ua.knu.classwork.cw08.task3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.stream.IntStream;

public class Task3Runner {
    public static void main(String[] args) {
        Path binFile = Paths.get("matrix_8_3.bin");
        double scalarK = 2.5;

        try {
            double[][] sampleMatrix = {
                    {1.5, -4.0, 0.0, 2.5},
                    {-7.5, 3.0, 7.5, -1.0},
                    {0.0, 5.5, -2.0, 4.0}
            };
            writeMatrixToBinary(binFile, sampleMatrix);
            processBinaryMatrix(binFile, scalarK);
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void writeMatrixToBinary(Path filePath, double[][] matrix) throws IOException {
        int m = matrix.length;
        int n = matrix[0].length;
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(filePath)))) {
            dos.writeInt(m);
            dos.writeInt(n);
            for (double[] row : matrix) {
                for (double val : row) {
                    dos.writeDouble(val);
                }
            }
        }
    }

    public static double[][] readMatrixFromBinary(Path filePath) throws IOException {
        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            throw new IllegalArgumentException("Binary file does not exist: " + filePath.toAbsolutePath());
        }

        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(Files.newInputStream(filePath)))) {
            int m = dis.readInt();
            int n = dis.readInt();
            if (m <= 0 || n <= 0) {
                throw new IllegalArgumentException("Matrix dimensions must be positive, got: " + m + "x" + n);
            }

            double[][] matrix = new double[m][n];
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    double val = dis.readDouble();
                    if (!Double.isFinite(val)) {
                        throw new IllegalArgumentException("Non-finite element at [" + r + "][" + c + "] in file: " + filePath);
                    }
                    matrix[r][c] = val;
                }
            }

            if (dis.read() != -1) {
                throw new IllegalArgumentException("Unexpected trailing bytes detected in file: " + filePath);
            }
            return matrix;
        } catch (EOFException e) {
            throw new IllegalArgumentException("Unexpected end of file while reading matrix from: " + filePath, e);
        }
    }

    public static void processBinaryMatrix(Path filePath, double k) throws IOException {
        if (!Double.isFinite(k)) {
            throw new IllegalArgumentException("Scalar k must be finite, got: " + k);
        }

        double[][] matrix = readMatrixFromBinary(filePath);
        int m = matrix.length;
        int n = matrix[0].length;

        DoubleSummaryStatistics stats = Arrays.stream(matrix)
                .flatMapToDouble(Arrays::stream)
                .summaryStatistics();

        long positiveCount = Arrays.stream(matrix)
                .flatMapToDouble(Arrays::stream)
                .filter(x -> x > 0.0)
                .count();

        long negativeCount = Arrays.stream(matrix)
                .flatMapToDouble(Arrays::stream)
                .filter(x -> x < 0.0)
                .count();

        long zeroCount = Arrays.stream(matrix)
                .flatMapToDouble(Arrays::stream)
                .filter(x -> x == 0.0)
                .count();

        double[] rowSums = Arrays.stream(matrix)
                .mapToDouble(row -> Arrays.stream(row).sum())
                .toArray();

        int[] bestPos = IntStream.range(0, m * n)
                .boxed()
                .reduce((idx1, idx2) -> {
                    double val1 = Math.abs(matrix[idx1 / n][idx1 % n]);
                    double val2 = Math.abs(matrix[idx2 / n][idx2 % n]);
                    return val2 > val1 ? idx2 : idx1;
                })
                .map(idx -> new int[]{idx / n, idx % n})
                .orElseThrow();

        double maxAbsValue = matrix[bestPos[0]][bestPos[1]];

        double[][] scaledMatrix = Arrays.stream(matrix)
                .map(row -> Arrays.stream(row).map(val -> val * k).toArray())
                .toArray(double[][]::new);

        double[] sortedElements = Arrays.stream(matrix)
                .flatMapToDouble(Arrays::stream)
                .sorted()
                .toArray();

        System.out.println("~ Original Matrix (" + m + "x" + n + ") ~");
        printMatrix(matrix);

        System.out.println("\n~ Matrix Statistics ~");
        System.out.printf("Sum: %.4f | Average: %.4f%n", stats.getSum(), stats.getAverage());
        System.out.printf("Positive: %d | Negative: %d | Zeros: %d%n", positiveCount, negativeCount, zeroCount);
        System.out.println("Row sums: " + Arrays.toString(rowSums));
        System.out.printf("First max |element|: %.4f at position (row=%d, col=%d)%n",
                maxAbsValue, bestPos[0], bestPos[1]);

        System.out.println("\n~ Scaled Matrix (multiplied by k = " + k + ") ~");
        printMatrix(scaledMatrix);

        System.out.println("\n~ Sorted Elements (Ascending) ~");
        System.out.println(Arrays.toString(sortedElements));
    }

    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}