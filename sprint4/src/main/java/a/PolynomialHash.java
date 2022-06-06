package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PolynomialHash {

    private static long calculatePolynomialHash(String s, int a, int m) {
        // a^3 mod p = ((a mod p)*(a mod p)*(a mod p)) mod p
        // h(s)=(s 1 q n−1 +s 2 q n−2 +⋯+s n−1 q+s n) mod R
        long hash = 0;
        for (char c : s.toCharArray()) {
            hash = (hash * a + c) % m;
        }
        return hash;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            String s = reader.readLine();

            System.out.println(calculatePolynomialHash(s, a, m));
        }
    }
}
