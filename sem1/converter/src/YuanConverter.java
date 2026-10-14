import java.util.Scanner;

public class YuanConverter {
  public static void main(String[] args) {
    final double ROUBLES_PER_YUAN = 11.91;

    Scanner input = new Scanner(System.in);

    System.out.print("Введите сумму в юанях: ");
    int yuan = input.nextInt();

    double roubles = ROUBLES_PER_YUAN * yuan;

    System.out.printf("%2d CNY = %2f RUB", yuan, roubles);
  }
}
