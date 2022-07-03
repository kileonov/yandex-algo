package e;

public class Solution {
    public static boolean treeSolution(Node head) {
        return isBST(head, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBST(Node head, int minVal, int maxVal) {
        if (head == null) return true;

        if (head.value <= minVal || head.value >= maxVal) return false;

        return isBST(head.left, minVal, head.value) && isBST(head.right, head.value, maxVal);
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

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /*
            5
           / \
          3   8
         / \
        1   4 <- node2
     */

    private static void test() {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        assert treeSolution(node5);
        System.out.println("treeSolution(node5) = " + treeSolution(node5));
        node2.value = 5;
        System.out.println("treeSolution(node5) = " + treeSolution(node5));
        assert !treeSolution(node5);
    }

    public static void main(String[] args) {
        test();
    }
}

