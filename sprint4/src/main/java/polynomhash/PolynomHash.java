package polynomhash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PolynomHash {

    private static long calculatePolynomHash(String str, int a, int m) {
        long hash = 0L;
        for (char ch : str.toCharArray()) {
            hash = (hash * a + ch) % m;
        }
        return hash;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            String str = reader.readLine();

            System.out.println(calculatePolynomHash(str, a, m));
        }
    }
}
