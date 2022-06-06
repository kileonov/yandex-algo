package n;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Flowerbed {

    private static List<Coordinate> mergeFlowerbeds(List<Coordinate> coordinates) {
        if (coordinates.size() == 1) return coordinates;

        List<Coordinate> leftSubList = mergeFlowerbeds(coordinates.subList(0, coordinates.size() / 2));
        List<Coordinate> rightSubList = mergeFlowerbeds(coordinates.subList(coordinates.size() / 2, coordinates.size()));

        int leftIdx = 0;
        int rightIdx = 0;
        Coordinate merged = null;
        List<Coordinate> result = new ArrayList<>(leftSubList.size() + rightSubList.size());
        while (leftIdx < leftSubList.size() && rightIdx < rightSubList.size()) {
            Coordinate left = leftSubList.get(leftIdx);
            Coordinate right = rightSubList.get(rightIdx);

            if (merged == null) {
                if (hasIntersection(left.left, right) || hasIntersection(right.left, left)) {
                    merged = createMaxLengthCoordinate(left, right);
                    leftIdx++;
                    rightIdx++;
                } else {
                    if (left.left <= right.left) {
                        result.add(left);
                        leftIdx++;
                    } else {
                        result.add(right);
                        rightIdx++;
                    }
                }
            } else {
                if (hasIntersection(left.left, merged)) {
                    merged = createMaxLengthCoordinate(left, merged);
                    leftIdx++;
                } else if (hasIntersection(right.left, merged)) {
                    merged = createMaxLengthCoordinate(right, merged);
                    rightIdx++;
                } else {
                    result.add(merged);
                    merged = null;
                }
            }
        }

        merged = mergeCoordinate(leftSubList, leftIdx, merged, result);
        merged = mergeCoordinate(rightSubList, rightIdx, merged, result);

        if (merged != null) {
            result.add(merged);
            merged = null;
        }

        return result;
    }

    private static Coordinate mergeCoordinate(List<Coordinate> subList, int idx, Coordinate merged, List<Coordinate> result) {
        while (idx < subList.size()) {
            Coordinate coordinate = subList.get(idx);
            if (merged == null) {
                result.add(coordinate);
                idx++;
            } else {
                if (hasIntersection(coordinate.left, merged)) {
                    merged = createMaxLengthCoordinate(coordinate, merged);
                    idx++;
                } else {
                    result.add(merged);
                    merged = null;
                }
            }
        }
        return merged;
    }

    private static Coordinate createMaxLengthCoordinate(Coordinate coordinate1, Coordinate coordinate2) {
        return new Coordinate(Math.min(coordinate1.left, coordinate2.left), Math.max(coordinate1.right, coordinate2.right));
    }

    private static boolean hasIntersection(int leftCoordinate, Coordinate coordinate) {
        return leftCoordinate >= coordinate.left && leftCoordinate <= coordinate.right;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            List<Coordinate> coordinates = readCoordinates(reader, n, " ");

            List<Coordinate> result = mergeFlowerbeds(coordinates);
            StringBuilder builder = new StringBuilder(result.size() * 4);
            result.forEach(coordinate -> builder.append(coordinate.left).append(" ").append(coordinate.right).append("\n"));
            System.out.println(builder);
        }
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
