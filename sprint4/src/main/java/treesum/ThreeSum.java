package treesum;

import util.LineToNumReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ThreeSum {

    static class Tripple {
        int first;
        int second;
        int third;

        public Tripple(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Tripple tripple = (Tripple) o;
            return first == tripple.first && second == tripple.second && third == tripple.third;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second, third);
        }

        @Override
        public String toString() {
            return " |" +
                     + first +
                    "," + second +
                    "," + third;
        }
    }

    private static Set<Tripple> calculateThreeSum(List<Integer> array, int sum) {
        Set<Tripple> triples = new HashSet<>();
        Set<Integer> history = new HashSet<>();

        for (int i = 0; i < array.size(); i++) {
            for (int j = i + 1; j < array.size(); j++) {
                int target = sum - array.get(i) - array.get(j);
                if (history.contains(target)) {
                    triples.add(new Tripple(target, array.get(i), array.get(j)));
                }
                history.add(array.get(i));
            }
        }

        return triples;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int sum = Integer.parseInt(reader.readLine());
            int n = Integer.parseInt(reader.readLine());
            List<Integer> array = LineToNumReader.readNonNegativeInts(reader, n, ',');
            System.out.println(calculateThreeSum(array, sum));
        }
    }
}
