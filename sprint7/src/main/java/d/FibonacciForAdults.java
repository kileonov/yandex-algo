package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FibonacciForAdults {
    private static long calculateFib(int n) {
        if (n <= 1) return 1;
        long pow = ((long) Math.pow(10.0, 9.0)) + 7;
        long f1 = 1;
        long f2 = 1;
        // 1 1 2 3 5 8
        // 0 1 2 3 4 5
        for (int i = 2; i <= n; i++) {
            final long temp = f1;
            f1 = f2;
            f2 = (f1 + temp) % pow;
        }
        return f2;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            long result = calculateFib(n);
            System.out.println(result);
        }
    }
}
