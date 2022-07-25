package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exchange {

    private static int getMaxProfit(List<Integer> stocksPrices) {
        int maxProfit = 0;
        for (int i = 0; i < stocksPrices.size() - 1; i++) {
            if (stocksPrices.get(i + 1) > stocksPrices.get(i)) {
                maxProfit += stocksPrices.get(i + 1) - stocksPrices.get(i);
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> stocksPrices = readNonNegativeInts(reader, n, ' ');
            System.out.println(getMaxProfit(stocksPrices));
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
