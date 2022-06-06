package tasks.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// OK passed ID: 67331998
public class MaxScore {
    private static final int MAX_MAP_SIZE = '.';
    private static final char SKIP_CHAR = '.';

    private static long calculateMaxScore(int k, char[][] matrix) {
        final Map<Character, Integer> chToCounter = new HashMap<>(MAX_MAP_SIZE);
        final var maxPresses = 2 * k;

        for (char[] chars : matrix) {
            for (char ch : chars) {
                chToCounter.merge(ch, 1, Integer::sum);
            }
        }

        return chToCounter.entrySet().stream()
                .filter(entry -> entry.getKey() != SKIP_CHAR && entry.getValue() <= maxPresses)
                .count();
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int k = readInt(reader);
            final char[][] matrix = readMatrix(reader);
            final long maxScore = calculateMaxScore(k, matrix);
            System.out.println(maxScore);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static char[][] readMatrix(BufferedReader reader) throws IOException {
        final char[][] matrix = new char[4][4];
        for (int i = 0; i < 4; i++) {
            final String textNums = reader.readLine();
            for (int j = 0; j < textNums.length(); j++) {
                matrix[i][j] = textNums.charAt(j);
            }
        }
        return matrix;
    }
}
