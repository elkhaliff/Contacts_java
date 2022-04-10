import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int secOfDay = scanner.nextInt();
        if (secOfDay > 0) {
            System.out.println(LocalTime.ofSecondOfDay(secOfDay));
        }

    }
}