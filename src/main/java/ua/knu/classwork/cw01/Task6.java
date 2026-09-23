package ua.knu.classwork.cw01;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Locale;
import java.util.Scanner;

public class Task6 {

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static double perimeter(double a, double b, double c) {
        return a + b + c;
    }

    public static double triangleAreaBySides(double a, double b, double c) {
        // Sort sides
        double[] sides = {a, b, c};
        java.util.Arrays.sort(sides);
        double cSide = sides[0];
        double bSide = sides[1];
        double aSide = sides[2];

        if (aSide >= bSide + cSide) {
            return 0.0;
        }

        double term1 = aSide + (bSide + cSide);
        double term2 = cSide - (aSide - bSide);
        double term3 = cSide + (aSide - bSide);
        double term4 = aSide + (bSide - cSide);

        return 0.25 * Math.sqrt(term1 * term2 * term3 * term4);
    }

    public static void runPrecisionVerification() {
        MathContext mc = new MathContext(50);

        BigDecimal a = new BigDecimal("3");
        BigDecimal two = new BigDecimal("2");
        BigDecimal powerTerm = BigDecimal.ONE.divide(two.pow(111), mc).multiply(new BigDecimal("3"), mc);
        BigDecimal b = new BigDecimal("3.5").add(powerTerm, mc);
        BigDecimal c = b;

        BigDecimal perimeter = a.add(b).add(c);

        BigDecimal p = perimeter.divide(two, mc);
        BigDecimal pMinusA = p.subtract(a, mc);
        BigDecimal pMinusB = p.subtract(b, mc);
        BigDecimal pMinusC = p.subtract(c, mc);

        BigDecimal underRoot = p.multiply(pMinusA, mc).multiply(pMinusB, mc).multiply(pMinusC, mc);
        BigDecimal area = underRoot.sqrt(mc);

        System.out.println("\n~ Verification for a = 3, b = c = 3.5 + 3 * 2^(-111) ~");
        System.out.println("High-precision Perimeter: " + perimeter.toPlainString());
        System.out.println("High-precision Area: " + area.toPlainString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ROOT);

        System.out.println("Enter vertex coordinates of the triangle:");
        System.out.println("Line 1: xA yA");
        System.out.println("Line 2: (empty line)");
        System.out.println("Line 3: xB yB");
        System.out.println("Line 4: (empty line)");
        System.out.println("Line 5: xC yC");

        if (!scanner.hasNextDouble()) {
            System.out.println("Error: Invalid coordinates.");
            return;
        }

        double ax = scanner.nextDouble();
        double ay = scanner.nextDouble();

        double bx = scanner.nextDouble();
        double by = scanner.nextDouble();

        double cx = scanner.nextDouble();
        double cy = scanner.nextDouble();

        // 1. Calculate side lengths using the distance function
        double sideA = distance(bx, by, cx, cy); // side BC opposite to A
        double sideB = distance(ax, ay, cx, cy); // side AC opposite to B
        double sideC = distance(ax, ay, bx, by); // side AB opposite to C

        // 2. Compute perimeter and area
        double triPerimeter = perimeter(sideA, sideB, sideC);
        double triArea = triangleAreaBySides(sideA, sideB, sideC);

        System.out.println("\n~ Triangle Results ~");
        System.out.printf(Locale.ROOT, "Side a: %.4f%n", sideA);
        System.out.printf(Locale.ROOT, "Side b: %.4f%n", sideB);
        System.out.printf(Locale.ROOT, "Side c: %.4f%n", sideC);
        System.out.printf(Locale.ROOT, "Perimeter: %.4f%n", triPerimeter);
        System.out.printf(Locale.ROOT, "Area: %.4f%n", triArea);

        runPrecisionVerification();
    }
}
