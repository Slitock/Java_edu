package app;

import geometry.Circle;
import geometry.ComparableCircle;
import geometry.GeometricObject;
import geometry.Rectangle;

public class TestComparableCircle {
    public static void main(String[] args) {
        ComparableCircle firstCircle = new ComparableCircle(4.0);
        ComparableCircle secondCircle = new ComparableCircle(6.5);

        System.out.println("=== Сравнение двух ComparableCircle ===");
        System.out.println("Круг 1: " + firstCircle);
        System.out.println("Круг 2: " + secondCircle);

        if (firstCircle.compareTo(secondCircle) > 0) {
            System.out.println("Наибольший: круг 1");
        } else if (firstCircle.compareTo(secondCircle) < 0) {
            System.out.println("Наибольший: круг 2");
        } else {
            System.out.println("Круги равны по площади");
        }

        System.out.println("\n=== Сравнение круга и прямоугольника через compareTo() ===");
        GeometricObject circle = new Circle(3.0);
        GeometricObject rectangle = new Rectangle(2.0, 5.0);

        System.out.println("Круг: " + circle);
        System.out.println("Прямоугольник: " + rectangle);

        if (circle.compareTo(rectangle) > 0) {
            System.out.println("Наибольшая фигура: круг");
        } else if (circle.compareTo(rectangle) < 0) {
            System.out.println("Наибольшая фигура: прямоугольник");
        } else {
            System.out.println("Площади фигур равны");
        }
    }
}
