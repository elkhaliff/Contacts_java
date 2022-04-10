import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final long hh = 11;
        Scanner scanner = new Scanner(System.in);
        System.out.println(LocalDateTime.parse(scanner.nextLine()).plusHours(hh).toLocalDate());
    }
}