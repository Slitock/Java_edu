package app;

import java.util.Scanner;

public class TestMonths {
    public static void main(String[] args) {
        String[] months = {"январь", "февраль", "март", "апрель", "май",
                "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
        int[] dom = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        Scanner input = new Scanner(System.in);

        System.out.print("Введите номер месяца (1-12): ");
        if (!input.hasNextInt()) {
            System.out.println("Ошибка: нужно целое число");
            return;
        }
        int month = input.nextInt();

        try {
            String name = months[month - 1];
            int days = dom[month - 1];

            if (month == 2) {
                System.out.print("Введите год: ");
                if (!input.hasNextInt()) {
                    System.out.println("Ошибка: год должен быть целым числом");
                    return;
                }
                int year = input.nextInt();
                days = isLeapYear(year) ? 29 : 28;
                System.out.println(name + " " + year + ": " + days + " дн.");
            } else {
                System.out.println(name + ": " + days + " дн.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Недопустимое число");
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
