package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFS {

    private enum Color {
        WHITE, GRAY, BLACK
    }

    public static void graphDFS(List<List<Integer>> edges, int s) {
        Map<Integer, Color> colors = new HashMap<>(edges.size() * 2 * 2);
        Map<Integer, SortedSet<Integer>> adjacentList = new HashMap<>();
        for (List<Integer> edge : edges) {
            Integer from = edge.get(0);
            Integer to = edge.get(1);

            if (!adjacentList.containsKey(from)) {
                adjacentList.put(from, new TreeSet<>());
            }
            adjacentList.get(from).add(to);

            if (!adjacentList.containsKey(to)) {
                adjacentList.put(to, new TreeSet<>());
            }
            adjacentList.get(to).add(from);

            colors.put(to, Color.WHITE);
            colors.put(from, Color.WHITE);
        }

        dfs(adjacentList, colors, s);
    }

    public static void dfs(Map<Integer, SortedSet<Integer>> adjacentList, Map<Integer, Color> colors, int s) {
        colors.put(s, Color.GRAY);
        System.out.print(s + " ");

        if (adjacentList.containsKey(s)) {
            for (Integer vertex : adjacentList.get(s)) {
                if (colors.get(vertex) == Color.WHITE) {
                    dfs(adjacentList, colors, vertex);
                }
            }
        }

        colors.put(s, Color.BLACK);
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

            int s = Integer.parseInt(reader.readLine());

            graphDFS(edges, s);
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
