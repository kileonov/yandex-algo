package j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SumFour {

    private static class Four implements Comparable<Four> {
        int first;
        int second;
        int third;
        int four;

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }

        public int getThird() {
            return third;
        }

        public int getFour() {
            return four;
        }

        public Four(int first, int second, int third, int four) {
            this.first = first;
            this.second = second;
            this.third = third;
            this.four = four;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Four four1 = (Four) o;
            return first == four1.first && second == four1.second && third == four1.third && four == four1.four;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second, third, four);
        }

        @Override
        public int compareTo(Four o) {
            return Comparator.comparing(Four::getFirst)
                    .thenComparing(Four::getSecond)
                    .thenComparing(Four::getThird)
                    .thenComparing(Four::getFour)
                    .compare(this, o);
        }
    }

    private static Set<Four> calculateFourSum(List<Integer> array, int sum) {
        Collections.sort(array);
        Set<Integer> history = new HashSet<>(array.size());
        SortedSet<Four> result = new TreeSet<>();

        for (int i = 0; i < array.size(); i++) {
            for (int j = i + 1; j < array.size(); j++) {
                for (int k = j + 1; k < array.size(); k++) {
                    int element = sum - array.get(i) - array.get(j) - array.get(k);
                    if (history.contains(element)) {
                        result.add(new Four(element, array.get(i), array.get(j), array.get(k)));
                    }
                }
            }
            history.add(array.get(i));
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            int sum = Integer.parseInt(reader.readLine());
            List<Integer> array = readNegativeInts(reader, n, ' ');

            StringBuilder builder = new StringBuilder(n);
            Set<Four> result = calculateFourSum(array, sum);
            builder.append(result.size()).append(System.lineSeparator());
            for (Four el : result) {
                builder.append(el.getFirst()).append(" ")
                        .append(el.getSecond()).append(" ")
                        .append(el.getThird()).append(" ")
                        .append(el.getFour())
                        .append(System.lineSeparator());
            }
            System.out.println(builder);
        }
    }

    public static List<Integer> readNegativeInts(BufferedReader reader, int n, char delimiter) throws IOException {
        if (n == 0) return Collections.emptyList();
        String line = reader.readLine();
        int currentNum = 0;
        boolean isNegative = false;
        List<Integer> elements = new ArrayList<>(n);

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '-') {
                isNegative = true;
            } else if (c != delimiter) {
                currentNum = 10 * currentNum + Character.getNumericValue(c);
            } else {
                elements.add(isNegative ? -currentNum : currentNum);
                currentNum = 0;
                isNegative = false;
            }
        }
        elements.add(isNegative ? -currentNum : currentNum);

        return elements;
    }
}
