package geometry;

public class Square extends GeometricObject implements Colorable {
    private double side;

    public Square() {
        this.side = 0;
    }

    public Square(double side) {
        setSide(side);
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Сторона квадрата должна быть больше 0");
        }
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public void howToColor() {
        System.out.println("Раскрасьте все четыре стороны.");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Сторона: %.2f | Площадь: %.2f | Периметр: %.2f",
                side, getArea(), getPerimeter());
    }
}
