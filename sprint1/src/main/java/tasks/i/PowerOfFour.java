package tasks.i;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class PowerOfFour {

    private static boolean isPowerOfFour(int n) {
        if (n == 0) return false;
        var currentNum = n;

        while (currentNum != 1) {
            var remainder = currentNum % 4;
            if (remainder != 0) return false;
            currentNum /= 4;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            if (isPowerOfFour(n)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

}
