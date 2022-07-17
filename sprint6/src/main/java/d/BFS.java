package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BFS {

    private static class AdjacentList {
        Map<Integer, List<Integer>> data;
        Map<Integer, Integer> colors;

        public AdjacentList(Map<Integer, List<Integer>> data, Map<Integer, Integer> colors) {
            this.data = data;
            this.colors = colors;
        }
    }

    public static void graphBFS(AdjacentList adjacentList, int s) {
        StringBuilder writer = new StringBuilder();
        bfs(adjacentList, s, writer);
        System.out.println(writer);
    }

    public static void bfs(AdjacentList adjacentList, int s, StringBuilder writer) {
        LinkedList<Integer> planned = new LinkedList<>();
        adjacentList.colors.put(s, 1);
        planned.addFirst(s);

        while (!planned.isEmpty()) {
            Integer vertex = planned.pollFirst();

            writer.append(vertex).append(" ");
            for (Integer childVertex : adjacentList.data.get(vertex)) {
                if (adjacentList.colors.get(childVertex) == 0) {
                    adjacentList.colors.put(childVertex, 1);
                    planned.addLast(childVertex);
                }
            }

            adjacentList.colors.put(vertex, 2);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] params = reader.readLine().split(" ");
            int n = Integer.parseInt(params[0]);
            int m = Integer.parseInt(params[1]);

            AdjacentList adjacentList = readVertices(reader, m, n);

            int s = Integer.parseInt(reader.readLine());
            graphBFS(adjacentList, s);
        }
    }

    public static AdjacentList readVertices(BufferedReader reader, int m, int n) throws IOException {
        Map<Integer, Integer> colors = new HashMap<>();
        Map<Integer, List<Integer>> adjacentListData = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adjacentListData.put(i, new LinkedList<>());
            colors.put(i, 0);
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
