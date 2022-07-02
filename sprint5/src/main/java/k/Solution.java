package k;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Solution {
    public static void printRange(Node root, int L, int R, BufferedWriter writer) throws IOException {
        if (root.getValue() >= L && root.getLeft() != null) printRange(root.getLeft(), L, R, writer);

        if (root.getValue() >= L && root.getValue() <= R) writer.append(Integer.toString(root.getValue())).append(System.lineSeparator());

        if (root.getValue() <= R && root.getRight() != null) printRange(root.getRight(), L, R, writer);
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;

        Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static void test() throws IOException {
        Node node5 = new Node(null, null, 15);
        Node node4 = new Node(null, node5, 11);
        Node node3 = new Node(null, null, 1);
        Node node2 = new Node(node3, null, 3);
        Node node1 = new Node(node2, node4, 7);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        printRange(node1, 6, 14, writer);
        writer.flush();

//        Node node1 = new Node(null, null, 2);
//        Node node2 = new Node(null, node1, 1);
//        Node node3 = new Node(null, null, 8);
//        Node node4 = new Node(null, node3, 8);
//        Node node5 = new Node(node4, null, 9);
//        Node node6 = new Node(node5, null, 10);
//        Node node7 = new Node(node2, node6, 5);
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//        printRange(node7, 2, 8, writer);
//        writer.flush();
        // expected output: 2 5 8 8
    }

    public static void main(String[] args) throws IOException {
        test();
    }
}
