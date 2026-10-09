package ua.knu.classwork.cw04.task7;

public class Quadrilateral {
    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;
    private static final double EPS = 1e-6;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double perimeter() {
        return a.distanceTo(b) + b.distanceTo(c) + c.distanceTo(d) + d.distanceTo(a);
    }

    public double area() {
        return 0.5 * Math.abs(
                (a.getX() * b.getY() + b.getX() * c.getY() + c.getX() * d.getY() + d.getX() * a.getY()) -
                        (b.getX() * a.getY() + c.getX() * b.getY() + d.getX() * c.getY() + a.getX() * d.getY())
        );
    }

    public QuadrilateralType getType() {
        double ab = a.distanceTo(b);
        double bc = b.distanceTo(c);
        double cd = c.distanceTo(d);
        double da = d.distanceTo(a);

        double diag1 = a.distanceTo(c);
        double diag2 = b.distanceTo(d);

        double abX = b.getX() - a.getX();
        double abY = b.getY() - a.getY();
        double dcX = c.getX() - d.getX();
        double dcY = c.getY() - d.getY();

        boolean isParallelogram = Math.abs(abX - dcX) < EPS && Math.abs(abY - dcY) < EPS;
        boolean allSidesEqual = Math.abs(ab - bc) < EPS && Math.abs(bc - cd) < EPS && Math.abs(cd - da) < EPS;
        boolean diagsEqual = Math.abs(diag1 - diag2) < EPS;

        if (isParallelogram) {
            if (allSidesEqual) {
                return diagsEqual ? QuadrilateralType.SQUARE : QuadrilateralType.RHOMBUS;
            } else if (diagsEqual) {
                return QuadrilateralType.RECTANGLE;
            }
        }
        return QuadrilateralType.ARBITRARY;
    }

    @Override
    public String toString() {
        return String.format("%s [Area: %.2f, Perimeter: %.2f]", getType(), area(), perimeter());
    }
}