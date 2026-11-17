package ua.knu.homework.hw07.task18;

import java.util.Objects;

public class Cell implements Comparable<Cell> {
    private final int r;
    private final int c;

    public Cell(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    @Override
    public int compareTo(Cell other) {
        if (this.r != other.r) {
            return Integer.compare(this.r, other.r);
        }
        return Integer.compare(this.c, other.c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return r == cell.r && c == cell.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }

    @Override
    public String toString() {
        return "(" + r + "," + c + ")";
    }
}