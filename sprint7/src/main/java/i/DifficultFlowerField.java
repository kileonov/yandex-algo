package i;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DifficultFlowerField {

    private static void calculateMaxFlowers(int[][] field, int n, int m) {
        for (int i = field.length - 2; i >= 0; i--) {
            for (int j = 1; j < field[i].length; j++) {
                int max = Math.max(field[i + 1][j], field[i][j - 1]);
                field[i][j] += max;
            }
        }

        int max = field[0][m];
        System.out.println(max);
        char up = 'U';
        char right = 'R';
        StringBuilder writer = new StringBuilder();
        int i = 0;
        int j = m;
        while (i != field.length - 2 || j != 1) {
            if (field[i][j - 1] > field[i + 1][j]) {
                writer.append(right);
                j--;
            } else if (i == field.length - 2 ) {
                writer.append(right);
                j--;
            } else {
                writer.append(up);
                i++;
            }
        }
        System.out.println(writer.reverse());
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokens = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokens.nextToken());
            int m = Integer.parseInt(tokens.nextToken());
            int[][] field = readFiled(n, m, reader);
            calculateMaxFlowers(field, n, m);
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
