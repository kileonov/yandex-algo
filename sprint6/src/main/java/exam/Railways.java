package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Railways {

    private enum Color {
        WHITE, GRAY, BLACK
    }

    private static class Railway {
        List<List<Integer>> adjacentList;
        List<Color> colors;

        public Railway(List<List<Integer>> adjacentList) {
            this.adjacentList = adjacentList;

            colors = new ArrayList<>(adjacentList.size());
            for (int i = 0; i < adjacentList.size(); i++) {
                colors.add(Color.WHITE);
            }
        }
    }

    public static void graphDFS(Railway railway) {
        System.out.println(dfs(railway, 1) ? "YES" : "NO");
    }

    public static boolean dfs(Railway railway, int s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        while (!stack.isEmpty()) {
            Integer city = stack.pop();
            final Color edgeColor = railway.colors.get(city);
            if (edgeColor == Color.WHITE) {
                railway.colors.set(city, Color.GRAY);
                stack.push(city);

                for (Integer neighbourCity : railway.adjacentList.get(city)) {
                    Color neighbourColor = railway.colors.get(neighbourCity);
                    if (neighbourColor == Color.WHITE) {
                        stack.push(neighbourCity);
                    } else if (neighbourColor ==  Color.GRAY) {
                        return false;
                    }
                }
            } else if (edgeColor == Color.GRAY) {
                railway.colors.set(city, Color.BLACK);
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());

            Railway railway = readAdjacentList(reader, n);

            graphDFS(railway);
        }
    }

    public static Railway readAdjacentList(BufferedReader reader, int n) throws IOException {
        List<List<Integer>> adjacentList = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adjacentList.add(new LinkedList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            String roads = reader.readLine();
            int from = i + 1;
            char[] chars = roads.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                int to = from + j + 1;
                if (roads.charAt(j) == ROAD_B) {
                    adjacentList.get(from).add(to);
                } else {
                    adjacentList.get(to).add(from);
                }
            }
        }

        return new Railway(adjacentList);
    }

    private static final char ROAD_B = 'B';
    private static final char ROAD_R = 'R';
}
