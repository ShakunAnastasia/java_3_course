package ua.knu.homework.hw07.task6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Task6Runner {
    public static void main(String[] args) {
        List<Double> poly1 = new ArrayList<>(Arrays.asList(2.0, 3.0, 1.0));
        List<Double> poly2 = new LinkedList<>(Arrays.asList(-1.0, 4.0));

        List<Double> product = PolynomialMultiplier.multiply(poly1, poly2);

        System.out.println("~ Polynomial Multiplication ~");
        System.out.println("P1(x) coefficients (asc power): " + poly1);
        System.out.println("P1(x) = " + PolynomialMultiplier.formatPolynomial(poly1));

        System.out.println("\nP2(x) coefficients (asc power): " + poly2);
        System.out.println("P2(x) = " + PolynomialMultiplier.formatPolynomial(poly2));

        System.out.println("\nResult P1(x) * P2(x) coefficients: " + product);
        System.out.println("Result = " + PolynomialMultiplier.formatPolynomial(product));
    }
}