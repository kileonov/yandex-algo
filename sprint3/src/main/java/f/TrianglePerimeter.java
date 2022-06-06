package f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrianglePerimeter {

    private static int calculateMaxTrianglePerimeter(List<Integer> sides) {
        Collections.sort(sides);
        int maxSideIdx = sides.size() - 1;

        int result = 0;
        while (maxSideIdx != 1 && result == 0) {
            int biggestSide = sides.get(maxSideIdx);
            int a = sides.get(maxSideIdx - 1);
            int b = sides.get(maxSideIdx - 2);
            if (a + b > biggestSide) {
                result = biggestSide + a + b;
            } else {
                maxSideIdx--;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> sides = readNonNegativeInts(reader, n, ' ');
            int result = calculateMaxTrianglePerimeter(sides);
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
