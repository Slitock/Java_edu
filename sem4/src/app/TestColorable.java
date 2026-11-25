package app;

import geometry.Circle;
import geometry.Colorable;
import geometry.GeometricObject;
import geometry.IllegalTriangleException;
import geometry.Rectangle;
import geometry.Square;
import geometry.Triangle;

public class TestColorable {
    public static void main(String[] args) throws IllegalTriangleException {
        GeometricObject[] figures = new GeometricObject[5];
        figures[0] = new Square(2.0);
        figures[1] = new Circle(3.0);
        figures[2] = new Rectangle(4.0, 2.0);
        figures[3] = new Square(5.0);
        figures[4] = new Triangle(3.0, 4.0, 5.0);

        System.out.println("=== Массив геометрических фигур ===");
        for (GeometricObject figure : figures) {
            System.out.printf("Площадь: %.2f | %s%n", figure.getArea(), figure);
            if (figure instanceof Colorable) {
                ((Colorable) figure).howToColor();
            }
        }

        System.out.println("\n=== Проверка конструктора Square со стороной -1 ===");
        try {
            new Square(-1.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Исключение IllegalArgumentException: " + e.getMessage());
        }
    }
}
