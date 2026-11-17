package ua.knu.homework.hw07.task6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PolynomialMultiplier {
    private static final double EPS = 1e-9;

    public static List<Double> multiply(List<Double> poly1, List<Double> poly2) {
        if (poly1 == null || poly2 == null || poly1.isEmpty() || poly2.isEmpty()) {
            return Collections.singletonList(0.0);
        }

        int resultSize = poly1.size() + poly2.size() - 1;
        List<Double> result = new ArrayList<>(Collections.nCopies(resultSize, 0.0));

        for (int i = 0; i < poly1.size(); i++) {
            for (int j = 0; j < poly2.size(); j++) {
                double current = result.get(i + j);
                result.set(i + j, current + poly1.get(i) * poly2.get(j));
            }
        }

        while (result.size() > 1 && Math.abs(result.get(result.size() - 1)) < EPS) {
            result.remove(result.size() - 1);
        }

        return result;
    }

    public static String formatPolynomial(List<Double> coeffs) {
        StringBuilder sb = new StringBuilder();
        boolean firstTerm = true;

        for (int power = coeffs.size() - 1; power >= 0; power--) {
            double c = coeffs.get(power);
            if (Math.abs(c) < EPS) {
                continue;
            }

            if (!firstTerm) {
                sb.append(c >= 0 ? " + " : " - ");
            } else if (c < 0) {
                sb.append("-");
            }

            double absC = Math.abs(c);
            if (power == 0 || Math.abs(absC - 1.0) >= EPS) {
                if (absC == (long) absC) {
                    sb.append((long) absC);
                } else {
                    sb.append(String.format("%.2f", absC));
                }
            }

            if (power == 1) {
                sb.append("x");
            } else if (power > 1) {
                sb.append("x^").append(power);
            }

            firstTerm = false;
        }

        return firstTerm ? "0" : sb.toString();
    }
}