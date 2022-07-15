package h;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFSTime {

    private static class AdjacentList {
        Map<Integer, List<Integer>> data;
        Map<Integer, Color> colors;
        int time;

        public AdjacentList(Map<Integer, List<Integer>> data, Map<Integer, Color> colors) {
            this.data = data;
            this.colors = colors;
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

    public static void graphDFS(AdjacentList adjacentList, int s) {
        Map<Integer, Timing> result = new HashMap<>();
        dfs(adjacentList, s, result);

        Object[] sortedVertices = result.keySet().toArray();
        Arrays.sort(sortedVertices);

        StringBuilder writer = new StringBuilder();
        for (int i = 0; i < sortedVertices.length; i++) {
            Integer vertex = (Integer) sortedVertices[i];
            Timing timing = result.get(vertex);
            writer.append(timing.entry).append(" ").append(timing.leave).append(System.lineSeparator());
        }
        System.out.println(writer);
    }

    public static void dfs(AdjacentList adjacentList, int s, Map<Integer, Timing> result) {
        final int entry = adjacentList.getAndInc();
        adjacentList.colors.put(s, Color.GRAY);

        if (adjacentList.data.containsKey(s)) {
            for (Integer vertex : adjacentList.data.get(s)) {
                if (adjacentList.colors.get(vertex) == Color.WHITE) {
                    dfs(adjacentList, vertex, result);
                }
            }
        }

        adjacentList.colors.put(s, Color.BLACK);
        final int leave = adjacentList.getAndInc();
        result.put(s, new Timing(entry, leave));
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] params = reader.readLine().split(" ");
            int n = Integer.parseInt(params[0]);
            int m = Integer.parseInt(params[1]);

            AdjacentList adjacentList = readVertices(reader, m);
            graphDFS(adjacentList, 1);
        }
    }

    public static AdjacentList readVertices(BufferedReader reader, int n) throws IOException {
        Map<Integer, Color> colors = new HashMap<>();
        Map<Integer, List<Integer>> adjacentListData = new HashMap<>();

        for (int i = 0; i < n; i++) {
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

            enrichAdjacentList(colors, adjacentListData, from, to);
        }

        for (List<Integer> value : adjacentListData.values()) {
            if (value.size() > 1) {
                Collections.sort(value);
            }
        }

        return new AdjacentList(adjacentListData, colors);
    }

    private static void enrichAdjacentList(Map<Integer, Color> colors, Map<Integer, List<Integer>> adjacentListData, int from, int to) {
        if (!adjacentListData.containsKey(from)) {
            adjacentListData.put(from, new ArrayList<>());
        }
        adjacentListData.get(from).add(to);

        colors.put(to, Color.WHITE);
        colors.put(from, Color.WHITE);
    }
}
