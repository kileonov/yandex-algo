package g;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wardrobe {

    private static List<Integer> countSort(List<Integer> colors) {
        List<Integer> availableColors = new ArrayList<>(List.of(0, 0, 0));

        for (int color : colors) {
            availableColors.set(color, availableColors.get(color) + 1);
        }

        List<Integer> result = new ArrayList<>(colors.size());
        for (int i = 0; i < availableColors.size(); i++) {
            for (int j = 0; j < availableColors.get(i); j++) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> colors = readNonNegativeInts(reader, n, ' ');
            List<Integer> sortedItems = countSort(colors);

            StringBuilder builder = new StringBuilder(sortedItems.size());
            sortedItems.forEach(item -> builder.append(item).append(" "));
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
