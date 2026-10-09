package ua.knu.homework.hw04.task4;

import java.util.Objects;

public class QuadraticEquation {
    private double a;
    private double b;
    private double c;
    private static final double EPS = 1e-9;

    public QuadraticEquation() {
        this(1.0, 0.0, 0.0);
    }

    public QuadraticEquation(double b, double c) {
        this(1.0, b, c);
    }

    public QuadraticEquation(double a, double b, double c) {
        if (Math.abs(a) < EPS) {
            throw new IllegalArgumentException("Coefficient 'a' cannot be zero in a quadratic equation.");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        if (Math.abs(a) < EPS) {
            throw new IllegalArgumentException("Coefficient 'a' cannot be zero.");
        }
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getDiscriminant() {
        return b * b - 4 * a * c;
    }

    public double[] findRoots() {
        double d = getDiscriminant();
        if (d < -EPS) {
            return new double[0];
        }
        if (Math.abs(d) < EPS) {
            return new double[]{-b / (2 * a)};
        }
        double sqrtD = Math.sqrt(d);
        double r1 = (-b - sqrtD) / (2 * a);
        double r2 = (-b + sqrtD) / (2 * a);
        return new double[]{Math.min(r1, r2), Math.max(r1, r2)};
    }

    public double getExtremumX() {
        return -b / (2 * a);
    }

    public double getExtremumY() {
        double x = getExtremumX();
        return a * x * x + b * x + c;
    }

    public String getExtremumType() {
        return a > 0 ? "Minimum" : "Maximum";
    }

    public String getMonotonicityIntervals() {
        double vx = getExtremumX();
        if (a > 0) {
            return String.format("Decreasing on (-inf; %.2f], Increasing on [%.2f; +inf)", vx, vx);
        } else {
            return String.format("Increasing on (-inf; %.2f], Decreasing on [%.2f; +inf)", vx, vx);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuadraticEquation that = (QuadraticEquation) o;
        return Math.abs(that.a - a) < EPS &&
                Math.abs(that.b - b) < EPS &&
                Math.abs(that.c - c) < EPS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return String.format("%.2fx^2 + %.2fx + %.2f = 0", a, b, c);
    }
}