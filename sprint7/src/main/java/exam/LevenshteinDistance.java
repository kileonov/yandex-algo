package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LevenshteinDistance {

    private static int calculateLevenshteinDistance(String s, String t) {
        final int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= t.length(); i++) {
            dp[0][i] = i;
        }

        int current;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                current = s.charAt(i - 1) == t.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(
                    dp[i - 1][j] + 1,
                    Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + current)
                );
            }
        }

        return dp[s.length()][t.length()];
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String s = reader.readLine();
            final String t = reader.readLine();

            final int result = calculateLevenshteinDistance(s, t);
            System.out.println(result);
        }
    }
}
