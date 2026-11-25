package app;

import java.util.Scanner;

import stacks.MyStack;
import stacks.MyStackArrayList;

public class TestMyStack {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] lines = new String[5];

        System.out.println("=== MyStack на композиции ===");
        System.out.println("Введите пять строк:");

        MyStack stack = new MyStack();
        for (int i = 0; i < lines.length; i++) {
            lines[i] = input.nextLine();
            stack.push(lines[i]);
        }

        System.out.println("Строки в обратном порядке:");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

        System.out.println("\n=== MyStack, унаследованный от ArrayList ===");
        MyStackArrayList arrayListStack = new MyStackArrayList();
        for (String line : lines) {
            arrayListStack.push(line);
        }

        System.out.println("Строки в обратном порядке:");
        while (!arrayListStack.isEmpty()) {
            System.out.println(arrayListStack.pop());
        }

        System.out.println("\n=== Глубокая копия ===");
        MyStack original = new MyStack();
        original.push("один");
        original.push("два");
        MyStack copy = original.clone();
        original.push("три");

        System.out.println("Оригинал: " + original);
        System.out.println("Копия: " + copy);
        System.out.println("Размер оригинала: " + original.getSize());
        System.out.println("Размер копии: " + copy.getSize());
        System.out.println("Это разные объекты: " + (original != copy));
    }
}
