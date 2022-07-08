package h;

import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    public static int treeSolution(Node head) {
        AtomicInteger result = new AtomicInteger(0);
        treeSolutionRec(head, "", result);
        return result.get();
    }

    public static void treeSolutionRec(Node head, String path, AtomicInteger result) {
        if (head == null) return;

        if (head.left == null && head.right == null) {
            result.addAndGet(Integer.parseInt(path + head.value));
            return;
        }

        treeSolutionRec(head.left, path + head.value, result);
        treeSolutionRec(head.right, path + head.value, result);
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

    /*
    10
    0 1 1 2
    1 1 3 None
    2 2 None 4
    3 3 5 6
    4 4 7 8
    5 2 None None
    6 6 None None
    7 7 None None
    8 8 9 None
    9 9 None None



                1
               / \
              1   2
             /     \
            3       4
           / \     / \
          2   6   7   8
                     /
                    9
          1132 + 1136 + 1247 + 12489
     */

    private static void test() {
        Node node9 = new Node(9, null, null);
        Node node8 = new Node(8, node9,    null);
        Node node7 = new Node(7, null, null);
        Node node6 = new Node(6, null, null);
        Node node5 = new Node(2, null, null);
        Node node4 = new Node(4, node7,    node8);
        Node node3 = new Node(3, node5,    node6);
        Node node2 = new Node(2, null, node4);
        Node node1 = new Node(1, node3,    null);
        Node node0 = new Node(1, node1,    node2);

//        Node node1 = new Node(2, null, null);
//        Node node2 = new Node(1, null, null);
//        Node node3 = new Node(3, node1, node2);
//        Node node4 = new Node(2, null, null);
//        Node node5 = new Node(1, node4, node3);
        int result = treeSolution(node0);
        System.out.println("result = " + result);
//        assert result == 275;
    }

    public static void main(String[] args) {
        test();
    }
}

