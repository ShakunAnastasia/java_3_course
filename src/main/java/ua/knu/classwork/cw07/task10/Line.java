package ua.knu.classwork.cw07.task10;

import java.util.Objects;

public class Line {
    private final int a;
    private final int b;
    private final int c;

    public Line(Point p1, Point p2) {
        int rawA = p1.getY() - p2.getY();
        int rawB = p2.getX() - p1.getX();
        int rawC = p1.getX() * p2.getY() - p2.getX() * p1.getY();

        int commonDivisor = gcd(gcd(Math.abs(rawA), Math.abs(rawB)), Math.abs(rawC));
        if (commonDivisor != 0) {
            rawA /= commonDivisor;
            rawB /= commonDivisor;
            rawC /= commonDivisor;
        }

        if (rawA < 0 || (rawA == 0 && rawB < 0)) {
            rawA = -rawA;
            rawB = -rawB;
            rawC = -rawC;
        }

        this.a = rawA;
        this.b = rawB;
        this.c = rawC;
    }

    private static int gcd(int x, int y) {
        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return a == line.a && b == line.b && c == line.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return String.format("%dx + %dy + %d = 0", a, b, c);
    }
}