package app;

import java.util.ArrayList;
import java.util.Arrays;

import shapes.Circle;
import util.CollectionUtils;

public class TestCollectionUtils {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 1, 4, 3, 5, 5));

        System.out.println("=== Удаление дубликатов ===");
        System.out.println("Исходный список: " + numbers);
        System.out.println("Без дубликатов: " + CollectionUtils.removeDuplicates(numbers));

        String[] languages = {"Java", "Python", "Rust", "C++"};

        System.out.println("\n=== Линейный поиск ===");
        System.out.println("Массив: " + Arrays.toString(languages));
        System.out.println("Позиция Rust: " + CollectionUtils.linearSearch(languages, "Rust"));
        System.out.println("Позиция Go: " + CollectionUtils.linearSearch(languages, "Go"));

        Circle[] circles = {new Circle(3.0), new Circle(7.0), new Circle(5.0)};

        System.out.println("\n=== Максимум в одномерном массиве ===");
        System.out.println("Круги: " + Arrays.toString(circles));
        System.out.println("Наибольший: " + CollectionUtils.max(circles));

        Circle[][] grid = {
                {new Circle(2.0), new Circle(9.0)},
                {new Circle(4.0), new Circle(6.0)}
        };

        System.out.println("\n=== Максимум в двумерном массиве ===");
        System.out.println("Матрица кругов: " + Arrays.deepToString(grid));
        System.out.println("Наибольший: " + CollectionUtils.max(grid));
    }
}
