package f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DistanceBetweenVertices {

    private static class AdjacentList {
        Map<Integer, List<Integer>> data;
        List<Integer> colors;
        List<Integer> distance;

        public AdjacentList(Map<Integer, List<Integer>> data, List<Integer> colors, List<Integer> distance) {
            this.data = data;
            this.colors = colors;
            this.distance = distance;
        }
    }

    public static void graphBFS(AdjacentList adjacentList, int s, int t) {
        bfs(adjacentList, s);
        System.out.println(adjacentList.distance.get(t));
    }

    public static void bfs(AdjacentList adjacentList, int s) {
        LinkedList<Integer> planned = new LinkedList<>();
        adjacentList.colors.set(s, 1);
        adjacentList.distance.set(s, 0);
        planned.addFirst(s);

        while (!planned.isEmpty()) {
            Integer vertex = planned.pollFirst();

            for (Integer childVertex : adjacentList.data.get(vertex)) {
                if (adjacentList.colors.get(childVertex) == 0) {
                    adjacentList.colors.set(childVertex, 1);
                    adjacentList.distance.set(childVertex, adjacentList.distance.get(vertex) + 1);
                    planned.addLast(childVertex);
                }
            }

            adjacentList.colors.set(vertex, 2);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] params = reader.readLine().split(" ");
            int n = Integer.parseInt(params[0]);
            int m = Integer.parseInt(params[1]);

            AdjacentList adjacentList = readVertices(reader, m, n);

            String[] vertices = reader.readLine().split(" ");
            int s = Integer.parseInt(vertices[0]);
            int t = Integer.parseInt(vertices[1]);
            graphBFS(adjacentList, s, t);
        }
    }

    public static AdjacentList readVertices(BufferedReader reader, int m, int n) throws IOException {
        List<Integer> colors = new ArrayList<>(n + 1);
        List<Integer> distance = new ArrayList<>(n + 1);
        colors.add(-1);
        distance.add(-1);
        Map<Integer, List<Integer>> adjacentListData = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adjacentListData.put(i, new LinkedList<>());
            colors.add(0);
            distance.add(-1);
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

        return new AdjacentList(adjacentListData, colors, distance);
    }
}
