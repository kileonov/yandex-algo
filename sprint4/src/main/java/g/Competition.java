package g;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Competition {

    private static int calculateMaxDraw(List<Integer> results) {
        Map<Integer, Integer> prefixSumToIdx = new HashMap<>();

        var maxIdx = 0;
        var currentSum = 0;
        for (int i = 0; i < results.size(); i++) {
            int currentScore = results.get(i);
            currentSum += currentScore == 0 ? -1 : 1;

            if (currentSum == 0) {
                maxIdx = Math.max(maxIdx, i + 1);
                continue;
            }

            if (!prefixSumToIdx.containsKey(currentSum)) {
                prefixSumToIdx.put(currentSum, i);
            } else {
                Integer idx = prefixSumToIdx.get(currentSum);
                maxIdx = Math.max(maxIdx, i - idx);
            }
        }

        return maxIdx;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> results = readNonNegativeInts(reader, n, ' ');
            System.out.println(calculateMaxDraw(results));
        }
    }

    public static List<Integer> readNonNegativeInts(BufferedReader reader, int n, char delimiter) throws IOException {
        if (n == 0) return Collections.emptyList();
        String line = reader.readLine();
        List<Integer> elements = new ArrayList<>(n);
        int currentNum = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c != delimiter) {
                currentNum = 10 * currentNum + Character.getNumericValue(c);
            }
            else {
                elements.add(currentNum);
                currentNum = 0;
            }
        }
        elements.add(currentNum);

        return elements;
    }
}
