package f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StairJumps {

    private static long calculateMaxJumps(int n, int k) {
        long mod = ((long) Math.pow(10.0d, 9.0d)) + 7;
        long[] stair = new long[n];
        stair[0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i + j < stair.length) {
                    stair[i + j] = (stair[i + j] + stair[i]) % mod;
                }
            }
        }

        return stair[n - 1];
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokens = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokens.nextToken());
            int k = Integer.parseInt(tokens.nextToken());
            long result = calculateMaxJumps(n, k);
            System.out.println(result);
        }
    }
}
