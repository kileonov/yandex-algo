package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Passed ID 69565263
/**
 * -- ПРИНЦИП РАБОТЫ --
 * Алгоритм может начать с любой вершины, поэтому берем первую попавшуюся. Из ребер соединяющих данную вершину с
 * другими, нужно выбрать с максимальным значением бюджета. Это ребро, будет именно тем ребром, которое входит
 * в максимальное остовное дерево. Помечаем эту вершину как посещенную, добавляем ребра (которые не соединяют
 * уже пройденные вершины) в массив ребер для прохода. Повторяем алгоритм до тех пор, пока либо массив ребер не станет
 * пустым или не обойдем все вершины.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * На каждом шаге пометки вершины как пройденной, выборки макс ребра уменьшается количество обрабатываем
 * вершин в изначальном графе (или остается неизменным если он несвязной). При этом мы точно находим макс ребро,
 * входящее в макс остовное дерево.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Time |e| - кол-во ребер, |v| - кол-во вершин:
 *  O(log|v|) - достать элемент из кучи для вершины. При этом это нужно сделать |e| раз.
 *  Итог: O(|e|log|v|)
 * Space:
 *  O(|v|) - массив для макс остовного графа
 *  O(|e|) - массив для посещенных вершин
 *  O(|e|) - массив для не посещенных вершин
 *  O(|e| + |v|) - список смежностей
 *  Итог: O(|v|) + O(|e|) + O(|e|) + O(|e| + |v|) = O(|e| + |v|) + O(|v|) + O(|e|)
 */
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
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
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
        private final List<MaxSpinningTree.Edge> data;

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
            if (idx <= 1) {
                return;
            }

            final int parentIdx = idx / 2;
            if (heap.get(idx).compareTo(heap.get(parentIdx)) > 0) {
                Collections.swap(heap, idx, parentIdx);
                siftUp(heap, parentIdx);
            }
        }

        public static void siftDown(List<MaxSpinningTree.Edge> heap, int idx) {
            final int lChildIdx = idx * 2;
            final int rChildIdx = idx * 2 + 1;

            if (lChildIdx >= heap.size()) {
                return;
            }

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
        final Map<Integer, List<MaxSpinningTree.Edge>> adjacentList = new HashMap<>(2 * n);
        for (int i = 1; i <= n; i++) {
            adjacentList.put(i, new LinkedList<>());
        }

        for (int i = 0; i < m; i++) {
            // поправил, раньше просто не проходило по времени/пространству, поэтому с первых спринтов ctrl c + ctrl v
            final StringTokenizer tokens = new StringTokenizer(reader.readLine());
            final int from = Integer.parseInt(tokens.nextToken());
            final int to = Integer.parseInt(tokens.nextToken());
            final int weight = Integer.parseInt(tokens.nextToken());

            adjacentList.get(from).add(new MaxSpinningTree.Edge(from, to, weight));
            adjacentList.get(to).add(new MaxSpinningTree.Edge(to, from, weight));
        }

        return MaxSpinningTree.of(adjacentList);
    }
}
