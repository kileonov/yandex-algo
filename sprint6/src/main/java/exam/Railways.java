package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Railways {

    private static final char ROAD_B = 'B';
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;

    private static class Railway {
        List<List<Integer>> cityAdjacentList;

        public Railway(int n) {
            cityAdjacentList = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) {
                cityAdjacentList.add(new LinkedList<>());
            }
        }

        public int getNumOfCities() {
            return cityAdjacentList.size();
        }

        public void addRoad(int from, int to) {
            cityAdjacentList.get(from).add(to);
        }
    }

    public static boolean hasCycledCity(Railway railway) {
        final int numOfCities = railway.getNumOfCities();
        final List<Integer> colors = new ArrayList<>(numOfCities);
        for (int i = 0; i < numOfCities; i++) {
            colors.add(WHITE);
        }

        for (int i = 1; i < numOfCities; i++) {
            if (colors.get(i) == WHITE) {
                if (dfs(railway.cityAdjacentList, colors, i)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean dfs(List<List<Integer>> cityAdjacentList, List<Integer> colors, int s) {
        colors.set(s, GRAY);

        for (Integer neighbourCity : cityAdjacentList.get(s)) {
            if (colors.get(neighbourCity) == GRAY) {
                return true;
            }

            if (colors.get(neighbourCity) == WHITE && dfs(cityAdjacentList, colors, neighbourCity)) {
                return true;
            }
        }

        colors.set(s, BLACK);
        return false;
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int n = Integer.parseInt(reader.readLine());

            final Railway railway = readAdjacentList(reader, n);

            final boolean result = hasCycledCity(railway);
            System.out.println(result ? "NO" : "YES");
        }
    }

    public static Railway readAdjacentList(BufferedReader reader, int n) throws IOException {
        final Railway railway = new Railway(n);

        for (int i = 0; i < n - 1; i++) {
            final String roads = reader.readLine();
            final int from = i + 1;
            final char[] chars = roads.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                final int to = from + j + 1;
                if (roads.charAt(j) == ROAD_B) {
                    railway.addRoad(from, to);
                } else {
                    railway.addRoad(to, from);
                }
            }
        }

        return railway;
    }
}
