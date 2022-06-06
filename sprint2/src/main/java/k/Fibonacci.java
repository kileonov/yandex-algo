package k;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonacci {

    private static int nextFibonacci(int n) {
        if (n == 0 || n == 1) return 1;
        return nextFibonacci(n - 1) + nextFibonacci(n - 2);
    }

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            int fibonacci = nextFibonacci(n);
            System.out.println(fibonacci);
        }
    }
}
