package ua.knu.classwork.cw08.task4;

public record Solution(double x, double y, double objective) {
    public boolean isFeasible() {
        return x >= 0.0 && y >= 0.0 && (x + y) <= 1.0;
    }
}