package a;

public class Solution {
    public static int treeSolution(Node head) {
        if (head == null) return Integer.MIN_VALUE;

        int l = head.left != null ? treeSolution(head.left) : Integer.MIN_VALUE;
        int r = head.right != null ? treeSolution(head.right) : Integer.MIN_VALUE;

        return Math.max(head.value, Math.max(l, r));
    }

    //    Comment it before submitting
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
        Node node4 = new Node(2);
        node4.left = node3;
        System.out.println("treeSolution(node4) = " + treeSolution(node4));
        assert treeSolution(node4) == 3;
    }

    public static void main(String[] args) {
        test();
    }
}

