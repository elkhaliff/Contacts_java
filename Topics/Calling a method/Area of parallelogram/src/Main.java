import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        countAreaOfParallelogram(scanner.nextInt(), scanner.nextInt());
    }

    public static void countAreaOfParallelogram(int b, int h) {
        System.out.println(b * h);
    }
}