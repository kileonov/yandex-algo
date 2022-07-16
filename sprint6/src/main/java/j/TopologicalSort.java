package j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopologicalSort {

    private static class AdjacentList {
        Map<Integer, List<Integer>> data;
        Map<Integer, Color> colors;
        List<Integer> topologicalOrder;
        int time;

        public AdjacentList(Map<Integer, List<Integer>> data, Map<Integer, Color> colors, List<Integer> topologicalOrder) {
            this.data = data;
            this.colors = colors;
            this.topologicalOrder = topologicalOrder;
        }

        public int getAndInc() {
            return time++;
        }
    }

    private static class Timing {
        int entry;
        int leave;

        public Timing(int entry, int leave) {
            this.entry = entry;
            this.leave = leave;
        }
    }

    private enum Color {
        WHITE, GRAY, BLACK
    }

    public static void graphDFS(AdjacentList adjacentList) {
        for (Map.Entry<Integer, Color> vertexToColor : adjacentList.colors.entrySet()) {
            if (vertexToColor.getValue() == Color.WHITE) {
                dfs(adjacentList, vertexToColor.getKey());
            }
        }

        StringBuilder writer = new StringBuilder();
        for (int i = adjacentList.topologicalOrder.size() - 1; i >= 0; i--) {
            Integer vertex = adjacentList.topologicalOrder.get(i);
            writer.append(vertex).append(" ");
        }
        System.out.println(writer);
    }

    public static void dfs(AdjacentList adjacentList, int s) {
        adjacentList.colors.put(s, Color.GRAY);

        if (adjacentList.data.containsKey(s)) {
            for (Integer vertex : adjacentList.data.get(s)) {
                if (adjacentList.colors.get(vertex) == Color.WHITE) {
                    dfs(adjacentList, vertex);
                }
            }
        }

        adjacentList.colors.put(s, Color.BLACK);
        adjacentList.topologicalOrder.add(s);
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
        Map<Integer, Color> colors = new HashMap<>();
        Map<Integer, List<Integer>> adjacentListData = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adjacentListData.put(i, new LinkedList<>());
            colors.put(i, Color.WHITE);
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
        }

        for (List<Integer> value : adjacentListData.values()) {
            if (value.size() > 1) {
                Collections.sort(value);
            }
        }

        return new AdjacentList(adjacentListData, colors, new ArrayList<>());
    }
}
