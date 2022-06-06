package o;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

public class TrashIndexDiff {

    private static void getAllDiffPairsRec(int[] areas, int idx, String result) {
        if (result.length() == 4) {
            System.out.println(result);
            return;
        }

        for (int i = idx; i < areas.length; i++) {
            getAllDiffPairsRec(areas, idx + i + 1, result + areas[i] + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            int[] areas = readNonNegativeInts(reader, n, ' ');
            int k = Integer.parseInt(reader.readLine());
            getAllDiffPairsRec(areas, 0, "");
        }
    }

    public static int[] readNonNegativeInts(BufferedReader reader, int n, char delimiter) throws IOException {
        int[] elements = new int[n];
        String line = reader.readLine();
        int currentNum = 0;
        int counter = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c != ' ') {
                currentNum = 10 * currentNum + Character.getNumericValue(c);
            }
            else {
                elements[counter++] = currentNum;
                currentNum = 0;
            }
        }
        elements[counter] = currentNum;
        return elements;
    }
}
