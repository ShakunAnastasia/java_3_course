package ua.knu.homework.hw04.task4;

import java.util.ArrayList;
import java.util.List;

public class Task4Runner {
    public static void main(String[] args) {
        List<QuadraticEquation> equations = new ArrayList<>();
        equations.add(new QuadraticEquation(1.0, -3.0, 2.0));   // Roots: 1.0, 2.0
        equations.add(new QuadraticEquation(1.0, -2.0, 1.0));   // Root: 1.0
        equations.add(new QuadraticEquation(2.0, 4.0, -6.0));   // Roots: -3.0, 1.0
        equations.add(new QuadraticEquation(-1.0, 2.0, 8.0));   // Roots: -2.0, 4.0
        equations.add(new QuadraticEquation(1.0, 0.0, 4.0));    // No real roots

        System.out.println("~~ Equations Analysis ~~");
        List<Double> allRoots = new ArrayList<>();

        for (QuadraticEquation eq : equations) {
            double[] roots = eq.findRoots();
            System.out.println("Equation: " + eq);
            System.out.printf("  Extremum (%s): vertex at (%.2f, %.2f)%n",
                    eq.getExtremumType(), eq.getExtremumX(), eq.getExtremumY());
            System.out.println("  Monotonicity: " + eq.getMonotonicityIntervals());
            System.out.print("  Real roots: ");
            if (roots.length == 0) {
                System.out.println("None (D < 0)");
            } else {
                for (double r : roots) {
                    System.out.printf("%.2f ", r);
                    allRoots.add(r);
                }
                System.out.println();
            }
        }

        if (!allRoots.isEmpty()) {
            double minRoot = allRoots.stream().min(Double::compare).orElse(0.0);
            double maxRoot = allRoots.stream().max(Double::compare).orElse(0.0);
            System.out.println("\n~~ Global Root Extrema ~~");
            System.out.printf("Minimum root across all equations: %.2f%n", minRoot);
            System.out.printf("Maximum root across all equations: %.2f%n", maxRoot);
        }
    }
}