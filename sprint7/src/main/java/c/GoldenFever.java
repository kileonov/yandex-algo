package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GoldenFever {

    static class GoldenHeap implements Comparable<GoldenHeap> {
        int price;
        int weight;

        public int getPrice() {
            return price;
        }

        public int getWeight() {
            return weight;
        }

        public GoldenHeap(int price, int weight) {
            this.price = price;
            this.weight = weight;
        }

        @Override
        public int compareTo(GoldenHeap o) {
            return Comparator.comparing(GoldenHeap::getPrice, Comparator.reverseOrder())
                    .thenComparing(GoldenHeap::getWeight)
                    .compare(this, o);
        }
    }

    private static long calculateMaxProfit(List<GoldenHeap> goldenHeaps, int m) {
        Collections.sort(goldenHeaps);

        long maxProfit = 0;
        int currentWeight = 0;
        for (GoldenHeap goldenHeap : goldenHeaps) {
            final int maxGoldenHeapToPutInBackpack = Math.min(m - currentWeight, goldenHeap.weight);
            maxProfit += (long) goldenHeap.price * maxGoldenHeapToPutInBackpack;
            currentWeight += maxGoldenHeapToPutInBackpack;
            if (currentWeight == m) return maxProfit;
        }

        return maxProfit;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int m = Integer.parseInt(reader.readLine());
            int n = Integer.parseInt(reader.readLine());

            List<GoldenHeap> goldenHeaps = readGoldenHeaps(reader, n);

            System.out.println(calculateMaxProfit(goldenHeaps, m));
        }
    }

    static List<GoldenHeap> readGoldenHeaps(BufferedReader reader, int n) throws IOException {
        List<GoldenHeap> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer tokens = new StringTokenizer(reader.readLine());
            GoldenHeap goldenHeap = new GoldenHeap(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
            result.add(goldenHeap);
        }

        return result;
    }
}
