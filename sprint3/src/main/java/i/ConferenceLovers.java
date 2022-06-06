package i;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConferenceLovers {

    static class University {
        public int id;
        public int counter;

        public University(int id, int counter) {
            this.id = id;
            this.counter = counter;
        }

        public void incrementCounter() {
            counter += 1;
        }
    }

    private static void insertionSort(University[] idCounters) {
        for (int i = 1; i < idCounters.length; i++) {
            int j = i;
            University temp = idCounters[j];
            while (j > 0 && idCounters[j - 1].counter < temp.counter) {
                idCounters[j] = idCounters[j - 1];
                j--;
            }
            idCounters[j] = temp;
        }
    }

    private static List<Integer> getMaxIdsInRange(List<Integer> ids, int k) {
        University[] idCounters = new University[10_000];
        for (int i = 0; i < idCounters.length; i++) {
            idCounters[i] = new University(i, 0);
        }

        for (Integer id : ids) {
            idCounters[id].incrementCounter();
        }

        insertionSort(idCounters);

        List<Integer> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            result.add(idCounters[i].id);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> ids = readNonNegativeInts(reader, n, ' ');
            int k = Integer.parseInt(reader.readLine());
            List<Integer> result = getMaxIdsInRange(ids, k);

            StringBuilder builder = new StringBuilder(result.size());
            result.forEach(id -> builder.append(id).append(" "));
            System.out.println(builder);
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

