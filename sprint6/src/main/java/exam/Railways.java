package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Passed ID 69443747
/**
 * -- ПРИНЦИП РАБОТЫ --
 * Движение идет к столице, при этом граф ориентированный и у нас есть всего 2 значения дорог.
 * Поэтому если дорога (ребро), равна B то соединяем два города слева направо (к столице), иначе наоборот.
 * Суть заключается в нахождении цикла в графе, если он есть, то значит 2 города соединены двумя типами дорог B и R.
 * Цикл можно легко найти с помощью 3 цветов. Белый - не начали обрабатывать, Черный - закончили, Серый - начали и в
 * процессе обработки, а значит цикл.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Если построить список смежностей именно таким способом, то цикл возникнет только тогда, когда встретится
 * направление из города CITY_X в город CITY_Y имеет 2 разные типы дорог из CITY_X в CITY_Y дорога B,
 * CITY_Y в CITY_X дорога R или наоборот.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Time |e| - кол-во ребер, |v| - кол-во вершин:
 *  O(|e| + |v|) - dfs
 *  Итог: O(|e|log|v|)
 * Space:
 *  O(|e||v|) - список смежностей городов
 *  O(|v|) - массив цветов для городов
 *  Итог: O(|e||v|) + O(|v|) = O(|e||v|)
 */
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
                cityAdjacentList.add(new ArrayList<>());
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
        final int[] colors = new int[numOfCities];
        for (int i = 0; i < numOfCities; i++) {
            colors[i] = WHITE;
        }

        for (int i = 1; i < numOfCities; i++) {
            if (colors[i] == WHITE) {
                if (dfs(railway.cityAdjacentList, colors, i)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean dfs(List<List<Integer>> cityAdjacentList, int[] colors, int s) {
        final LinkedList<Integer> stack = new LinkedList<>();
        stack.add(s);

        while (!stack.isEmpty()) {
            final Integer city = stack.removeLast();
            final int edgeColor = colors[city];
            if (edgeColor == WHITE) {
                colors[city] = GRAY;
                stack.add(city);

                for (Integer neighbourCity : cityAdjacentList.get(city)) {
                    final int neighbourColor = colors[neighbourCity];

                    if (neighbourColor ==  GRAY) {
                        return true;
                    }

                    if (neighbourColor == WHITE) {
                        stack.add(neighbourCity);
                    }
                }
            } else if (edgeColor == GRAY) {
                colors[city] = BLACK;
            }
        }

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
            for (int j = 0; j < roads.length(); j++) {
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
