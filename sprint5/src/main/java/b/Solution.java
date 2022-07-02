package b;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static boolean treeSolution(Node head) {
        Map<Integer, Counter> depthToCounters = new HashMap<>();
        treeSolutionRec(head, depthToCounters, 0, 0);

        System.out.println(depthToCounters);

        return false;
    }

    public static void treeSolutionRec(Node head, Map<Integer, Counter> depthToCounters, int depth, int position) {
        if (head == null) return;

        treeSolutionRec(head.left, depthToCounters, depth + 1, -1);
        treeSolutionRec(head.right, depthToCounters, depth + 1, 1);

        if (!depthToCounters.containsKey(depth)) {
            depthToCounters.put(depth, new Counter(0, 0));
        }

        if (position == -1) {
            int left = depthToCounters.containsKey(depth + 1) ? depthToCounters.get(depth + 1).left : 0;
            depthToCounters.get(depth).left += left + 1;
        } else if (position == 1) {
            int right = depthToCounters.containsKey(depth + 1) ? depthToCounters.get(depth + 1).right : 0;
            depthToCounters.get(depth).right += right + 1;
        } else {
            int left = depthToCounters.containsKey(depth + 1) ? depthToCounters.get(depth + 1).left : 0;
            int right = depthToCounters.containsKey(depth + 1) ? depthToCounters.get(depth + 1).right : 0;
            depthToCounters.get(depth).left += left + 1;
            depthToCounters.get(depth).right += right + 1;
        }
    }

    static class Counter {
        int left;
        int right;

        public Counter(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Counter{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    // Comment it before submitting
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;
        treeSolution(node5);
    }

    public static void main(String[] args) {
        test();
    }
}
