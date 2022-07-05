package b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    public static boolean treeSolution(Node head) {
        ArrayList<Boolean> result = new ArrayList<>(Collections.singletonList(true));
        treeSolutionRec(head, result);
        return result.get(0);
    }

    public static int treeSolutionRec(Node head, List<Boolean> result) {
        if (head == null) return 0;

        int l = treeSolutionRec(head.left, result);
        int r = treeSolutionRec(head.right, result);

        if (Math.abs(l - r) > 1) result.set(0, false);

        return Math.max(l, r) + 1;
    }

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
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
        System.out.println("treeSolution(node5) = " + treeSolution(node5));
    }

    public static void main(String[] args) {
        test();
    }
}
