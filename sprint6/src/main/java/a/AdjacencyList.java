package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AdjacencyList {

    private static void convertToAdjacencyList(List<List<Integer>> edges, int n) {
        Map<Integer, List<Integer>> result = new HashMap<>(200);

        for (List<Integer> edge : edges) {
            Integer from = edge.get(0);
            Integer to = edge.get(1);

            if (!result.containsKey(from)) {
                result.put(from, new ArrayList<>());
            }
            result.get(from).add(to);
        }

        StringBuilder writer = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (result.containsKey(i)) {
                List<Integer> connectedVertices = result.get(i);
                writer.append(connectedVertices.size()).append(" ");
                for (Integer connectedVertex : connectedVertices) {
                    writer.append(connectedVertex).append(" ");
                }
            } else {
                writer.append(0);
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
            convertToAdjacencyList(edges, n);
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
