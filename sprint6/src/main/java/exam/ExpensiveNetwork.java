package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ExpensiveNetwork {

    private static class MaxSpinningTree {
        Map<Integer, List<Edge>> adjacentList;

        List<Edge> maxSpanningTree;
        Set<Integer> notAddedVertices;
        Heap edges;

        private MaxSpinningTree() {}

        public static MaxSpinningTree of(Map<Integer, List<Edge>> adjacentList) {
            final MaxSpinningTree maxSpinningTree = new MaxSpinningTree();
            maxSpinningTree.adjacentList = adjacentList;

            maxSpinningTree.maxSpanningTree = new ArrayList<>(adjacentList.size());
            maxSpinningTree.notAddedVertices = new HashSet<>(adjacentList.size() * 2);
            maxSpinningTree.edges = new Heap(adjacentList.size() + 2);

            return maxSpinningTree;
        }

        private static class Edge implements Comparable<Edge> {
            int from;
            int to;
            int weight;

            public Edge(int from, int to, int weight) {
                this.from = from;
                this.to = to;
                this.weight = weight;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                final Edge edge = (Edge) o;
                return from == edge.from && to == edge.to && weight == edge.weight;
            }

            @Override
            public int hashCode() {
                return Objects.hash(from, to, weight);
            }

            @Override
            public int compareTo(Edge o) {
                return weight - o.weight;
            }
        }

        private static void addVertex(Integer v, MaxSpinningTree maxSpinningTree) {
            maxSpinningTree.notAddedVertices.remove(v);

            final List<Edge> graphEdges = maxSpinningTree.adjacentList.get(v);
            for (Edge graphEdge : graphEdges) {
                if (maxSpinningTree.notAddedVertices.contains(graphEdge.to)) {
                    maxSpinningTree.edges.addElement(graphEdge);
                }
            }
        }

        private static Edge extractMaxEdge(Heap edges) {
            return edges.removeFirstElement();
        }

        private static void calculateMaxSpanningGraph(MaxSpinningTree maxSpinningTree) {
            maxSpinningTree.notAddedVertices.addAll(maxSpinningTree.adjacentList.keySet());

            final Integer v = maxSpinningTree.adjacentList.keySet().iterator().next();
            addVertex(v, maxSpinningTree);

            while (!maxSpinningTree.notAddedVertices.isEmpty() && !maxSpinningTree.edges.isEmpty()) {
                final Edge e = extractMaxEdge(maxSpinningTree.edges);
                if (maxSpinningTree.notAddedVertices.contains(e.to)) {
                    maxSpinningTree.maxSpanningTree.add(e);
                    addVertex(e.to, maxSpinningTree);
                }
            }

            if (!maxSpinningTree.notAddedVertices.isEmpty()) {
                System.out.println("Oops! I did it again");
            } else {
                int result = 0;
                for (Edge edge : maxSpinningTree.maxSpanningTree) {
                    result += edge.weight;
                }
                System.out.println(result);
            }
        }
    }

    private static class Heap {
        private List<MaxSpinningTree.Edge> data;

        public Heap(int size) {
            this.data = new ArrayList<>(size + 1);
            this.data.add(null);
        }

        public void addElement(MaxSpinningTree.Edge element) {
            final int index = data.size();
            data.add(index, element);
            siftUp(data, index);
        }

        public MaxSpinningTree.Edge removeFirstElement() {
            final MaxSpinningTree.Edge max = data.get(1);
            data.set(1, data.get(data.size() - 1));
            siftDown(data, 1);
            data.remove(data.size() - 1);
            return max;
        }

        public boolean isEmpty() {
            return data.size() <= 1;
        }

        public static void siftUp(List<MaxSpinningTree.Edge> heap, int idx) {
            if (idx <= 1) return;

            final int parentIdx = idx / 2;
            if (heap.get(idx).compareTo(heap.get(parentIdx)) > 0) {
                Collections.swap(heap, idx, parentIdx);
                siftUp(heap, parentIdx);
            }
        }

        public static void siftDown(List<MaxSpinningTree.Edge> heap, int idx) {
            final int lChildIdx = idx * 2;
            final int rChildIdx = idx * 2 + 1;

            if (lChildIdx >= heap.size()) return;

            final int maxChildIdx;
            if (rChildIdx < heap.size() && heap.get(rChildIdx).compareTo(heap.get(lChildIdx)) > 0) {
                maxChildIdx = rChildIdx;
            } else {
                maxChildIdx = lChildIdx;
            }

            if (heap.get(idx).compareTo(heap.get(maxChildIdx)) < 0) {
                Collections.swap(heap, idx, maxChildIdx);
                siftDown(heap, maxChildIdx);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String[] params = reader.readLine().split(" ");
            final int n = Integer.parseInt(params[0]);
            final int m = Integer.parseInt(params[1]);

            final MaxSpinningTree adjacentList = readEdges(reader, n, m);

            MaxSpinningTree.calculateMaxSpanningGraph(adjacentList);
        }
    }

    public static MaxSpinningTree readEdges(BufferedReader reader, int n, int m) throws IOException {
        Map<Integer, List<MaxSpinningTree.Edge>> adjacentList = new HashMap<>(2 * n);
        for (int i = 1; i <= n; i++) {
            adjacentList.put(i, new LinkedList<>());
        }

        for (int i = 0; i < m; i++) {
            final String line = reader.readLine();
            int from = -1;
            int to = -1;
            final int weight;
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
            weight = currentNum;

            adjacentList.get(from).add(new MaxSpinningTree.Edge(from, to, weight));
            adjacentList.get(to).add(new MaxSpinningTree.Edge(to, from, weight));
        }

        return MaxSpinningTree.of(adjacentList);
    }
}
