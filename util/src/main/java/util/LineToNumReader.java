package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LineToNumReader {
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
            } else if (c != ' ') {
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

    static public class Coordinate {
        public int left;
        public int right;

        public Coordinate(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static List<Coordinate> readCoordinates(BufferedReader reader, int n, String delimiter) throws IOException {
        List<Coordinate> coordinates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            String[] flowerbed = reader.readLine().split(delimiter);
            int left = Integer.parseInt(flowerbed[0]);
            int right = Integer.parseInt(flowerbed[1]);
            coordinates.add(new Coordinate(left, right));
        }
        return coordinates;
    }
}
