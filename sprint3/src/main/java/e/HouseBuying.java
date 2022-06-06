package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HouseBuying {

    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        var leftIdx = 0; var rightIdx = 0;
        var result = new ArrayList<Integer>(left.size() + right.size());

        while (leftIdx < left.size() && rightIdx < right.size()) {
            Integer leftElement = left.get(leftIdx);
            Integer rightElement = right.get(rightIdx);
            if (leftElement > rightElement) {
                result.add(rightElement);
                rightIdx++;
            } else {
                result.add(leftElement);
                leftIdx++;
            }
        }

        while (leftIdx < left.size()) {
            Integer element = left.get(leftIdx);
            result.add(element);
            leftIdx++;
        }

        while (rightIdx < right.size()) {
            Integer element = right.get(rightIdx);
            result.add(element);
            rightIdx++;
        }

        return result;
    }

    private static List<Integer> mergeSort(List<Integer> prices) {
        if (prices.size() == 1) return prices;

        var left = mergeSort(prices.subList(0, prices.size() / 2));
        var right = mergeSort(prices.subList(prices.size() / 2, prices.size()));

        return merge(left, right);
    }

    private static int calculateMostValuableDeal(List<Integer> prices, int k) {
        List<Integer> sortedPrices = mergeSort(prices);

        int idx = 0;
        while (idx < sortedPrices.size() && k >= sortedPrices.get(idx)) {
            k -= sortedPrices.get(idx);
            idx++;
        }

        return idx;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> nk = readNonNegativeInts(reader, 2, ' ');
            int n = nk.get(0);
            int k = nk.get(1);

            List<Integer> prices = readNonNegativeInts(reader, n, ' ');
            var result = calculateMostValuableDeal(prices, k);
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
