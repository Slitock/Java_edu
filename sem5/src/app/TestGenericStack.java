package app;

import stacks.GenericStack;

public class TestGenericStack {
    public static void main(String[] args) {
        GenericStack<String> stack = new GenericStack<>();

        stack.push("Первый");
        stack.push("Второй");
        stack.push("Третий");

        System.out.println("=== Стек после трёх push ===");
        System.out.println(stack);
        System.out.println("Размер: " + stack.getSize());
        System.out.println("Вершина: " + stack.peek());

        System.out.println("\n=== Проверка роста внутреннего массива ===");
        for (int i = 1; i <= 12; i++) {
            stack.push("Элемент " + i);
        }
        System.out.println("Размер после 15 push: " + stack.getSize());
        System.out.println(stack);

        System.out.println("\n=== Извлечение элементов ===");
        while (!stack.isEmpty()) {
            System.out.println("pop: " + stack.pop());
        }
        System.out.println("Стек пуст: " + stack.isEmpty());
    }
}
