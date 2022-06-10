package a;

public class Solution {

    public static int treeSolution(Node head) {
        if (head == null) return 0;

        int current = head.value;
        int left = treeSolution(head.left);
        int right = treeSolution(head.right);

        return Math.max(current, Math.max(left, right));
    }

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            right = null;
            left = null;
        }
    }

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        int solution = treeSolution(node4);
        System.out.println("solution = " + solution);
        assert solution == 3;
    }

    public static void main(String[] args) {
        test();
    }
}
