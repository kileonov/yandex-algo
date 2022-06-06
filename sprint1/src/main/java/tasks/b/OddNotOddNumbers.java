package tasks.b;

import java.util.Scanner;

public class OddNotOddNumbers {

    private static boolean isOdd(int num) {
        return num % 2 != 0;
    }

    private static boolean checkParity(int a, int b, int c) {
        boolean isOddA = isOdd(a);
        boolean isOddB = isOdd(b);
        boolean isOddC = isOdd(c);

        return isOddA && isOddB && isOddC || !isOddA && !isOddB && !isOddC;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        if (checkParity(a, b, c)) {
            System.out.println("WIN");
        } else {
            System.out.println("FAIL");
        }
        scanner.close();
    }
}
