package l;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LeprechaunGold {

    private static int calculateMaxWeight(List<Integer> goldBars, int m) {
        int[][] dp = new int[goldBars.size()][m + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                // previous
                int previous = i > 0 ? dp[i - 1][j] : 0;
                // current + max
                int current = j - goldBars.get(i) >= 0 ? goldBars.get(i) + (i > 0 ? dp[i - 1][j - goldBars.get(i)] : 0) : 0;
                dp[i][j] = Math.max(previous, current);
            }
        }

        return dp[goldBars.size() - 1][m];
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer paramTokens = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(paramTokens.nextToken());
            int m = Integer.parseInt(paramTokens.nextToken());

            List<Integer> goldBars = new ArrayList<>(n);
            StringTokenizer goldBarTokens = new StringTokenizer(reader.readLine());
            while (goldBarTokens.hasMoreTokens()) {
                goldBars.add(Integer.parseInt(goldBarTokens.nextToken()));
            }

            int result = calculateMaxWeight(goldBars, m);
            System.out.println(result);
        }
    }
}
