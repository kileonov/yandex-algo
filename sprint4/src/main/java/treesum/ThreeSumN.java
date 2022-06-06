package treesum;

import util.LineToNumReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ThreeSumN {

    private static class Triples {
        int first;
        int second;
        int third;

        public Triples(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public String toString() {
            return " |" +
                    + first +
                    "," + second +
                    "," + third;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Triples triples = (Triples) o;
            return first == triples.first && second == triples.second && third == triples.third;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second, third);
        }
    }

    private static Set<Triples> calculateThreeSum(List<Integer> array, int sum) {
        Set<Integer> history = new HashSet<>();
        Set<Triples> result = new HashSet<>();

        for (int i = 0; i < array.size() - 1; i++) {
            for (int j = i + 1; j < array.size(); j++) {
                int element = sum - array.get(i) - array.get(j);
                if (history.contains(element)) {
                    result.add(new Triples(array.get(i), array.get(j), element));
                }
            }
            history.add(array.get(i));
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int sum = Integer.parseInt(reader.readLine());
            int n = Integer.parseInt(reader.readLine());
            List<Integer> array = LineToNumReader.readNonNegativeInts(reader, n, ',');

            calculateThreeSum(array, sum).forEach(System.out::println);
        }
    }
}
