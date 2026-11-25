import java.util.Scanner;

public class Bmi {
    public static void main(String[] args) {
        final int SMS_PER_METER = 100;

        Scanner input = new Scanner(System.in);

        System.out.print("Введите вес в килограммах: ");
        double weight = input.nextDouble();

        System.out.print("Введите рост в сантиметрах: ");
        double height = input.nextDouble();

        double bmi = weight / (height / SMS_PER_METER * height / SMS_PER_METER);

        System.out.printf("BMI = %.2f%n", bmi);

        if (bmi < 18.5) {
            System.out.println("Недостаточный вес");
        } else if (bmi < 25) {
            System.out.println("Норма");
        } else if (bmi < 30) {
            System.out.println("Избыточный вес");
        } else {
            System.out.println("Ожирение");
        }
    }
}
