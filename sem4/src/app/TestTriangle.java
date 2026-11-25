package app;

import java.util.Scanner;

import geometry.IllegalTriangleException;
import geometry.Triangle;

public class TestTriangle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Введите три стороны треугольника: ");
        double side1 = input.nextDouble();
        double side2 = input.nextDouble();
        double side3 = input.nextDouble();

        System.out.print("Введите цвет: ");
        String color = input.next();

        System.out.print("Треугольник закрашен? (true/false): ");
        boolean filled = input.nextBoolean();

        try {
            Triangle triangle = new Triangle(side1, side2, side3);
            triangle.setColor(color);
            triangle.setFilled(filled);

            System.out.println("\n=== Треугольник ===");
            System.out.println(triangle);
            System.out.printf("Площадь: %.2f%n", triangle.getArea());
            System.out.printf("Периметр: %.2f%n", triangle.getPerimeter());
            System.out.println("Цвет: " + triangle.getColor());
            System.out.println("Заливка: " + triangle.isFilled());
        } catch (IllegalTriangleException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Проверка IllegalTriangleException на сторонах 1, 2, 5 ===");
        try {
            Triangle invalidTriangle = new Triangle(1, 2, 5);
            System.out.println(invalidTriangle);
        } catch (IllegalTriangleException e) {
            System.out.println("Исключение IllegalTriangleException: " + e.getMessage());
        }
    }
}
