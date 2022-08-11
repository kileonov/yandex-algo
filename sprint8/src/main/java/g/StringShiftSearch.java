package g;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StringShiftSearch {

    private static String calculateSubstr(int[] x, int[] a) {
        StringBuilder writer = new StringBuilder();
        if (a.length > x.length) return writer.toString();

        for (int i = 0; i <= x.length - a.length; i++) {
            boolean match = true;

            int diff = 0;
            for (int j = 0; j < a.length; j++) {
                if (j == 0) diff = x[i + j] - a[j];
                if (x[i + j] - a[j] != diff) {
                    match = false;
                    break;
                }
            }

            if (match) writer.append(i + 1).append(" ");
        }

        return writer.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            StringTokenizer xTokens = new StringTokenizer(reader.readLine());
            int[] x = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = Integer.parseInt(xTokens.nextToken());
            }

            int m = Integer.parseInt(reader.readLine());
            StringTokenizer aTokens = new StringTokenizer(reader.readLine());
            int[] a = new int[m];
            for (int i = 0; i < m; i++) {
                a[i] = Integer.parseInt(aTokens.nextToken());
            }

            String result = calculateSubstr(x, a);
            System.out.println(result);
        }
    }
}
