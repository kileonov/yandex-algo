package tasks.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// OK passed ID: 67331995
public class ClosestZero {

    private static int[] getClosestEmptyArea(int[] areas) {
        final int[] result = new int[areas.length];

        int distanceToNextSpace = 0;
        int previousSpace = -1;
        for (int i = 0; i < areas.length; i++) {
            // считаем дома с первого шага
            if (areas[i] != 0) {
                result[i] = ++distanceToNextSpace;
                continue;
            }

            if (previousSpace == -1) {
                recalculateInitialHouseDistance(result, distanceToNextSpace, i);
            } else {
                recalculateMidToCurrentEmptyDistnce(result, previousSpace, i);
            }
            distanceToNextSpace = 0;
            result[i] = 0;
            previousSpace = i;

        }

        return result;
    }

    private static void recalculateMidToCurrentEmptyDistnce(int[] result, int previousSpace, int i) {
        for (int j = (previousSpace + i) / 2 + 1; j < i; j++) {
            result[j] = i - j;
        }
    }

    private static void recalculateInitialHouseDistance(int[] result, int counter, int i) {
        for (int j = 0; j < i; j++) {
            result[j] = counter--;
        }
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int streetLength = Integer.parseInt(reader.readLine());
            final int[] areas = readList(streetLength, reader);
            final int[] closestEmptyAreas = getClosestEmptyArea(areas);

            final StringBuilder outputBuffer = new StringBuilder();
            for (int area : closestEmptyAreas) {
                outputBuffer.append(area).append(" ");
            }
            System.out.println(outputBuffer);
        }
    }

    private static int[] readList(int n, BufferedReader reader) throws IOException {
        final String[] textNumbers = reader.readLine().split(" ");

        final int[] numbers = new int[n];
        for (int i = 0; i < textNumbers.length; i++) {
            numbers[i] = Integer.parseInt(textNumbers[i]);
        }
        return numbers;
    }
}
