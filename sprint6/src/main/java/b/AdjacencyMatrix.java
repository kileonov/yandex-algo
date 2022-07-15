package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdjacencyMatrix {

    private static void convertToAdjacencyMatrix(List<List<Integer>> edges, int n) {
        int[][] result = new int[n][n];

        for (List<Integer> edge : edges) {
            Integer from = edge.get(0);
            Integer to = edge.get(1);

            result[from - 1][to - 1] = 1;
        }

        StringBuilder writer = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                writer.append(result[i][j]).append(" ");
            }
            writer.append(System.lineSeparator());
        }
        System.out.println(writer);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] params = reader.readLine().split(" ");
            int n = Integer.parseInt(params[0]);
            int m = Integer.parseInt(params[1]);

            List<List<Integer>> edges = new ArrayList<>(m);
            for (int i = 0; i < m; i++) {
                edges.add(readNonNegativeInts(reader, 2, ' '));
            }
            convertToAdjacencyMatrix(edges, n);
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
