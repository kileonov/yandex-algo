package i;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SharedSubArray {

    private static int calculateMaxSubArray(List<Integer> arr1, List<Integer> arr2) {
        final Map<Integer, List<Integer>> numToPos1 = getNumToIndices(arr1);
        final Map<Integer, List<Integer>> numToPos2 = getNumToIndices(arr2);

        var max = 0;
        for (Map.Entry<Integer, List<Integer>> entry : numToPos1.entrySet()) {
            final Integer num = entry.getKey();

            final List<Integer> indices1 = numToPos1.get(num);

            if (numToPos2.containsKey(num)) {
                final List<Integer> indices2 = numToPos2.get(num);
                for (Integer idx1 : indices1) {
                    for (Integer idx2 : indices2) {
                        var currentMax = 1;
                        var curIdx1 = idx1;
                        var curIdx2 = idx2;
                        while (++curIdx1 < arr1.size() && ++curIdx2 < arr2.size()) {
                            if (arr1.get(curIdx1).equals(arr2.get(curIdx2))) currentMax++;
                            else break;
                        }
                        max = Math.max(max, currentMax);
                    }
                }
            }
        }

        return max;
    }

    private static Map<Integer, List<Integer>> getNumToIndices(List<Integer> arr) {
        Map<Integer, List<Integer>> numToPos = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            if (!numToPos.containsKey(arr.get(i))) {
                numToPos.put(arr.get(i), new ArrayList<>());
            }
            numToPos.get(arr.get(i)).add(i);
        }
        return numToPos;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> arr1 = readNonNegativeInts(reader, n, ' ');
            int m = Integer.parseInt(reader.readLine());
            List<Integer> arr2 = readNonNegativeInts(reader, m, ' ');
            int result = calculateMaxSubArray(arr1, arr2);
            System.out.println(result);
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
