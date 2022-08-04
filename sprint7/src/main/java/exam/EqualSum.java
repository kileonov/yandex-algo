package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EqualSum {

    private static boolean hasEqualSum(int[] points) {
        int sum = 0;
        for (int point : points) {
            sum += point;
        }
        if (sum % 2 != 0) return false;

        final int halfSum = sum / 2;
        final boolean[][] dp = new boolean[halfSum + 1][points.length + 1];
        Arrays.fill(dp[0], true);
        for (int i = 1; i <= halfSum; i++) {
            for (int j = 1; j <= points.length; j++) {
                if (i - points[j - 1] >= 0) {
                    dp[i][j] = dp[i][j - 1] || dp[i - points[j - 1]][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[halfSum][points.length];
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int n = Integer.parseInt(reader.readLine());
            final int[] points = new int[n];
            final StringTokenizer tokens = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                points[i] = Integer.parseInt(tokens.nextToken());
            }
            final boolean result = hasEqualSum(points);
            System.out.println(result ? "True" : "False");
        }
    }
}
