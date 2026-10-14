import java.util.Scanner;

public class YuanConverterWithSuffix {
    public static void main(String[] args) {
        final double ROUBLES_PER_YUAN = 11.91;

        Scanner input = new Scanner(System.in);

        System.out.print("Введите сумму в китайских юанях: ");
        int yuan = input.nextInt();

        double roubles = ROUBLES_PER_YUAN * yuan;

        int lastDigit = Math.abs(yuan) % 10;
        int lastTwoDigits = Math.abs(yuan) % 100;
        String suffix;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) {
            suffix = "китайских юаней";
        } else if (lastDigit == 1) {
            suffix = "китайский юань";
        } else if (lastDigit >= 2 && lastDigit <= 4) {
            suffix = "китайских юаня";
        } else {
            suffix = "китайских юаней";
        }

        System.out.printf("%d %s = %.2f российских рублей\n", yuan, suffix, roubles);
    }
}
