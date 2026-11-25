package shapes;

public class Circle implements Comparable<Circle> {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override //сравнение кругов по площади
    public int compareTo(Circle other) {
        return Double.compare(this.getArea(), other.getArea());
    }

    @Override
    public String toString() {
        return String.format("Круг: радиус = %.2f | площадь = %.2f", radius, getArea());
    }
}
