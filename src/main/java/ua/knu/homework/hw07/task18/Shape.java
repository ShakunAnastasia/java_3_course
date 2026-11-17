package ua.knu.homework.hw07.task18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Shape {
    private final String canonicalSignature;
    private final List<Cell> normalizedCells;

    public Shape(List<Cell> rawCells) {
        List<String> signatures = new ArrayList<>();
        List<List<Cell>> orientations = new ArrayList<>();

        List<Cell> current = new ArrayList<>(rawCells);
        for (int rot = 0; rot < 4; rot++) {
            List<Cell> shifted = normalizeTranslation(current);
            Collections.sort(shifted);
            orientations.add(shifted);
            signatures.add(buildSignature(shifted));
            current = rotate90(current);
        }

        int bestIdx = 0;
        for (int i = 1; i < signatures.size(); i++) {
            if (signatures.get(i).compareTo(signatures.get(bestIdx)) < 0) {
                bestIdx = i;
            }
        }

        this.canonicalSignature = signatures.get(bestIdx);
        this.normalizedCells = Collections.unmodifiableList(orientations.get(bestIdx));
    }

    private static List<Cell> rotate90(List<Cell> cells) {
        List<Cell> rotated = new ArrayList<>(cells.size());
        for (Cell cell : cells) {
            rotated.add(new Cell(cell.getC(), -cell.getR()));
        }
        return rotated;
    }

    private static List<Cell> normalizeTranslation(List<Cell> cells) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        for (Cell cell : cells) {
            if (cell.getR() < minR) minR = cell.getR();
            if (cell.getC() < minC) minC = cell.getC();
        }
        List<Cell> shifted = new ArrayList<>(cells.size());
        for (Cell cell : cells) {
            shifted.add(new Cell(cell.getR() - minR, cell.getC() - minC));
        }
        return shifted;
    }

    private static String buildSignature(List<Cell> sortedCells) {
        StringBuilder sb = new StringBuilder();
        for (Cell cell : sortedCells) {
            sb.append(cell.getR()).append(":").append(cell.getC()).append(";");
        }
        return sb.toString();
    }

    public String renderAscii() {
        int maxR = 0;
        int maxC = 0;
        for (Cell cell : normalizedCells) {
            if (cell.getR() > maxR) maxR = cell.getR();
            if (cell.getC() > maxC) maxC = cell.getC();
        }
        char[][] canvas = new char[maxR + 1][maxC + 1];
        for (int r = 0; r <= maxR; r++) {
            for (int c = 0; c <= maxC; c++) {
                canvas[r][c] = '.';
            }
        }
        for (Cell cell : normalizedCells) {
            canvas[cell.getR()][cell.getC()] = '#';
        }
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r <= maxR; r++) {
            for (int c = 0; c <= maxC; c++) {
                sb.append(canvas[r][c]).append(' ');
            }
            if (r < maxR) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Objects.equals(canonicalSignature, shape.canonicalSignature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(canonicalSignature);
    }
}