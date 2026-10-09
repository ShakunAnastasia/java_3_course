package ua.knu.classwork.cw04.task7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Task7Runner {
    public static void main(String[] args) {
        List<Quadrilateral> quads = List.of(
                new Quadrilateral(new Point(0, 0), new Point(0, 2), new Point(2, 2), new Point(2, 0)),
                new Quadrilateral(new Point(0, 0), new Point(0, 4), new Point(4, 4), new Point(4, 0)),
                new Quadrilateral(new Point(0, 0), new Point(0, 2), new Point(5, 2), new Point(5, 0)),
                new Quadrilateral(new Point(0, 0), new Point(0, 1), new Point(3, 1), new Point(3, 0)),
                new Quadrilateral(new Point(0, 3), new Point(4, 0), new Point(0, -3), new Point(-4, 0)),
                new Quadrilateral(new Point(0, 0), new Point(1, 4), new Point(4, 4), new Point(5, 0))
        );

        Map<QuadrilateralType, List<Quadrilateral>> groups = new EnumMap<>(QuadrilateralType.class);
        for (QuadrilateralType t : QuadrilateralType.values()) {
            groups.put(t, new ArrayList<>());
        }
        for (Quadrilateral q : quads) {
            groups.get(q.getType()).add(q);
        }

        System.out.println("~ Quadrilateral Group Statistics ~");
        for (Map.Entry<QuadrilateralType, List<Quadrilateral>> entry : groups.entrySet()) {
            QuadrilateralType type = entry.getKey();
            List<Quadrilateral> list = entry.getValue();

            System.out.printf("%nGroup: %s | Count: %d%n", type, list.size());
            if (!list.isEmpty()) {
                Quadrilateral minArea = Collections.min(list, Comparator.comparingDouble(Quadrilateral::area));
                Quadrilateral maxArea = Collections.max(list, Comparator.comparingDouble(Quadrilateral::area));
                Quadrilateral minPerimeter = Collections.min(list, Comparator.comparingDouble(Quadrilateral::perimeter));
                Quadrilateral maxPerimeter = Collections.max(list, Comparator.comparingDouble(Quadrilateral::perimeter));

                System.out.printf("  Min Area: %.2f | Max Area: %.2f%n", minArea.area(), maxArea.area());
                System.out.printf("  Min Perimeter: %.2f | Max Perimeter: %.2f%n", minPerimeter.perimeter(), maxPerimeter.perimeter());
            }
        }
    }
}