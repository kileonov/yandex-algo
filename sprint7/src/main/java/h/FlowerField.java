package h;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FlowerField {

    private static int calculateMaxFlowers(int[][] field, int n, int m) {
        for (int i = field.length - 2; i >= 0; i--) {
            for (int j = 1; j < field[i].length; j++) {
                int max = Math.max(field[i + 1][j], field[i][j - 1]);
                field[i][j] += max;
            }
        }
        return field[0][m];
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokens = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokens.nextToken());
            int m = Integer.parseInt(tokens.nextToken());
            int[][] field = readFiled(n, m, reader);
            System.out.println(calculateMaxFlowers(field, n, m));
        }
    }

    private static int[][] readFiled(int n, int m, BufferedReader reader) throws IOException {
        int[][] field = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            String row = reader.readLine();
            for (int j = 0; j < row.length(); j++) {
                field[i][j + 1] = Character.getNumericValue(row.charAt(j));
            }
        }

        return field;
    }
}
