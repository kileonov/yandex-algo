package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ConnectedComponents {

    private static class AdjacentList {
        Map<Integer, List<Integer>> data;
        Map<Integer, Integer> colors;
        int connectedComponentCounter;

        public AdjacentList(Map<Integer, List<Integer>> data, Map<Integer, Integer> colors) {
            this.data = data;
            this.colors = colors;
        }

        public int incAndGet() {
            return ++connectedComponentCounter;
        }
    }

    public static void graphDFS(AdjacentList adjacentList) {
        for (Map.Entry<Integer, Integer> vertexToColor : adjacentList.colors.entrySet()) {
            if (vertexToColor.getValue() == -1) {
                int component = adjacentList.incAndGet();
                dfs(adjacentList, vertexToColor.getKey(), component);
            }
        }

        StringBuilder writer = new StringBuilder();
        writer.append(adjacentList.connectedComponentCounter).append(System.lineSeparator());

        Map<Integer, SortedSet<Integer>> sortedComponents = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : adjacentList.colors.entrySet()) {
            if (!sortedComponents.containsKey(entry.getValue())) {
                sortedComponents.put(entry.getValue(), new TreeSet<>());
            }
            sortedComponents.get(entry.getValue()).add(entry.getKey());
        }
        for (int i = 1; i <= adjacentList.connectedComponentCounter; i++) {
            SortedSet<Integer> verticesInComponent = sortedComponents.get(i);
            for (Integer vertex : verticesInComponent) {
                writer.append(vertex).append(" ");
            }
            writer.append(System.lineSeparator());
        }
        System.out.println(writer);
    }

    public static void dfs(AdjacentList adjacentList, int s, int component) {
        adjacentList.colors.put(s, 0);

        if (adjacentList.data.containsKey(s)) {
            for (Integer vertex : adjacentList.data.get(s)) {
                if (adjacentList.colors.get(vertex) == -1) {
                    dfs(adjacentList, vertex, component);
                }
            }
        }

        adjacentList.colors.put(s, component);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] params = reader.readLine().split(" ");
            int n = Integer.parseInt(params[0]);
            int m = Integer.parseInt(params[1]);

            AdjacentList adjacentList = readVertices(reader, m, n);
            graphDFS(adjacentList);
        }
    }

    public static AdjacentList readVertices(BufferedReader reader, int m, int n) throws IOException {
        Map<Integer, Integer> colors = new HashMap<>();
        Map<Integer, List<Integer>> adjacentListData = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adjacentListData.put(i, new LinkedList<>());
            colors.put(i, -1);
        }

        for (int i = 0; i < m; i++) {
            String line = reader.readLine();
            int from = -1;
            int to;
            int currentNum = 0;
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c != ' ') {
                    currentNum = 10 * currentNum + Character.getNumericValue(c);
                } else {
                    if (from == -1) from = currentNum;
                    currentNum = 0;
                }
            }
            to = currentNum;

            adjacentListData.get(from).add(to);
            adjacentListData.get(to).add(from);
        }

        for (List<Integer> value : adjacentListData.values()) {
            if (value.size() > 1) {
                Collections.sort(value);
            }
        }

        return new AdjacentList(adjacentListData, colors);
    }
}
