package h;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static util.LineToNumReader.readNonNegativeInts;

public class BiggestNum {

    private static class BiggestNumComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer current, Integer next) {
            String c = current.toString();
            String n = next.toString();

            int firstCombination = Integer.parseInt(c + n);
            int secondCombination = Integer.parseInt(n + c);

            return -Integer.compare(firstCombination, secondCombination);
        }
    }

    private static void constructBiggestNum(List<Integer> elements) {
        elements.sort(new BiggestNumComparator());

        StringBuilder resultBuilder = new StringBuilder();
        elements.forEach(resultBuilder::append);
        System.out.println(resultBuilder);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> elements = readNonNegativeInts(reader, n, ' ');

            constructBiggestNum(elements);
        }
    }
}
