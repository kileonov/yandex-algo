package l;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FibonacciModule {

    private static int nextFibonacciModule(int n, int k) {
        int mod = (int) Math.pow(10.0, k);
        if (n == 0 || n == 1) {
            return 1 % mod;
        }
        int fib1 = 1;
        int fib2 = 1;

        int current = fib1 + fib2;
        for (int i = 2; i <= n; i++) {
            current = fib1 + fib2;
            fib1 = fib2;
            fib2 = current % mod;
        }

        return current % mod;
    }

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] params = reader.readLine().split(" ");
            int n = Integer.parseInt(params[0]);
            int k = Integer.parseInt(params[1]);
            long fibonacci = nextFibonacciModule(n, k);
            System.out.println(fibonacci);
        }
    }
}
