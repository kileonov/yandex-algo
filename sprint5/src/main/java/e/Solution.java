package e;

public class Solution {

    public static boolean treeSolution(Node head) {
        if (head == null) {
            return true;
        } else if (head.left != null && head.right != null) {
            return  compareTo(head.value, head.left.value, true) &&
                    compareTo(head.value, head.right.value, false) &&
                    treeSolution(head.left) &&
                    treeSolution(head.right);
        } else if (head.left != null && head.right == null) {
            return  compareTo(head.value, head.left.value, true) &&
                    treeSolution(head.left);
        } else if (head.left == null && head.right != null) {
            return  compareTo(head.value, head.right.value, false) &&
                    treeSolution(head.right);
        } else {
            return true;
        }
    }

    private static boolean compareTo(int headValue, int value, boolean isLeft) {
        if (isLeft) {
            return headValue > value;
        } else {
            return value > headValue;
        }
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

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private static void test() {


        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        boolean sol1 = treeSolution(node5);
        System.out.println("sol1 = " + sol1);
        assert sol1;
        node2.value = 5;
        boolean sol2 = !treeSolution(node5);
        System.out.println("sol2 = " + sol2);
        assert sol2;
    }

    public static void main(String[] args) {
        test();
    }
}
