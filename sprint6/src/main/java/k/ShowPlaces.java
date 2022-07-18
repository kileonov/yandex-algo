package k;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShowPlaces {

    private static class Vertex {
        int vertex;
        int edge;

        public Vertex(int vertex, int edge) {
            this.vertex = vertex;
            this.edge = edge;
        }
    }

    private static class AdjacentList {
        Map<Integer, List<Vertex>> data;
        List<Boolean> visited;
        List<Integer> distance;
        List<Integer> previous;

        public AdjacentList(Map<Integer, List<Vertex>> data) {
            this.data = data;
        }

        public void clearData(int n) {
            visited = new ArrayList<>(n + 1);
            visited.add(false);
            distance = new ArrayList<>(n + 1);
            distance.add(null);
            previous = new ArrayList<>(n + 1);
            previous.add(null);

            for (int i = 1; i <= n; i++) {
                visited.add(false);
                distance.add(Integer.MAX_VALUE);
                previous.add(null);
            }
        }
    }

    public static void graphBFS(AdjacentList adjacentList, int n) {
        StringBuilder writer = new StringBuilder();
        for (Integer vertex : adjacentList.data.keySet()) {
            Dijkstra(adjacentList, vertex);
            for (Integer distance : adjacentList.distance) {
                if (distance == null) continue;

                if (distance == Integer.MAX_VALUE) writer.append(-1).append(" ");
                else writer.append(distance).append(" ");
            }
            writer.append(System.lineSeparator());
            adjacentList.clearData(n);
        }
        System.out.println(writer);
    }

    private static int getMinDistNotVisitedVertex(AdjacentList adjacentList) {
        int currentMin = Integer.MAX_VALUE;
        int currentMinVertex = -1;

        for (Integer vertex : adjacentList.data.keySet()) {
            if (!adjacentList.visited.get(vertex) && adjacentList.distance.get(vertex) < currentMin) {
                currentMin = adjacentList.distance.get(vertex);
                currentMinVertex = vertex;
            }
        }
        return currentMinVertex;
    }

    public static void Dijkstra(AdjacentList adjacentList, int s) {
        adjacentList.distance.set(s, 0);

        int u;
        while ((u = getMinDistNotVisitedVertex(adjacentList)) != -1) {
            adjacentList.visited.set(u, true);
            List<Vertex> neighbours = adjacentList.data.get(u);
            for (Vertex neighbour : neighbours) {
                relax(u, neighbour, adjacentList);
            }
        }
    }

    private static void relax(int s, Vertex neighbour, AdjacentList adjacentList) {
        if (adjacentList.distance.get(neighbour.vertex) > adjacentList.distance.get(s) + neighbour.edge) {
            adjacentList.distance.set(neighbour.vertex, adjacentList.distance.get(s) + neighbour.edge);
            adjacentList.previous.set(neighbour.vertex, s);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] params = reader.readLine().split(" ");
            int n = Integer.parseInt(params[0]);
            int m = Integer.parseInt(params[1]);

            AdjacentList adjacentList = readVertices(reader, n, m);

            graphBFS(adjacentList, n);
        }
    }

    public static AdjacentList readVertices(BufferedReader reader, int n, int m) throws IOException {
        Map<Integer, List<Vertex>> data = new HashMap<>(n * 2);
        for (int i = 1; i <= n; i++) {
            data.put(i, new LinkedList<>());
        }

        for (int i = 0; i < m; i++) {
            String line = reader.readLine();
            int from = -1;
            int to = -1;
            int length;
            int currentNum = 0;
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c != ' ') {
                    currentNum = 10 * currentNum + Character.getNumericValue(c);
                } else {
                    if (from == -1) from = currentNum;
                    else if (to == -1) to = currentNum;
                    currentNum = 0;
                }
            }
            length = currentNum;

            data.get(from).add(new Vertex(to, length));
            data.get(to).add(new Vertex(from, length));
        }

        AdjacentList adjacentList = new AdjacentList(data);
        adjacentList.clearData(n);
        return adjacentList;
    }
}
