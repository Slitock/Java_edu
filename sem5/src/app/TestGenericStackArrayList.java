package app;

import java.util.Scanner;

import stacks.GenericStackArrayList;

public class TestGenericStackArrayList {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GenericStackArrayList<String> stack = new GenericStackArrayList<>();

        System.out.println("Введите пять строк:");
        for (int i = 0; i < 5; i++) {
            stack.push(input.nextLine());
        }

        System.out.println("\n=== Строки в обратном порядке ===");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
