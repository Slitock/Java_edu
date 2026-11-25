package app;

import geometry.Circle;
import geometry.GeometricObject;
import geometry.Rectangle;

public class TestMax {
    public static void main(String[] args) {
        Circle firstCircle = new Circle(3.0);
        Circle secondCircle = new Circle(5.0);

        Rectangle firstRectangle = new Rectangle(2.0, 4.0);
        Rectangle secondRectangle = new Rectangle(3.0, 3.0);

        System.out.println("=== Поиск наибольшего из двух кругов ===");
        GeometricObject maxCircle = GeometricObject.max(firstCircle, secondCircle);
        System.out.println("Круг 1: " + firstCircle);
        System.out.println("Круг 2: " + secondCircle);
        System.out.println("Наибольший: " + maxCircle);

        System.out.println("\n=== Поиск наибольшего из двух прямоугольников ===");
        GeometricObject maxRectangle = GeometricObject.max(firstRectangle, secondRectangle);
        System.out.println("Прямоугольник 1: " + firstRectangle);
        System.out.println("Прямоугольник 2: " + secondRectangle);
        System.out.println("Наибольший: " + maxRectangle);
    }
}
