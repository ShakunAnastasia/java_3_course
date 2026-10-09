package ua.knu.classwork.cw04.task7;

public class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point other) {
        return Math.hypot(this.x - other.x, this.y - other.y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}