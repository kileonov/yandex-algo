package c;

public class Solution {
    public static boolean treeSolution(Node head) {
        if (head ==  null) return false;

        return isTreeAnagram(head.left, head.right);
    }

    public static boolean isTreeAnagram(Node left, Node right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        if (left.value != right.value) return false;
        else return isTreeAnagram(left.left, right.right) && isTreeAnagram(left.right, right.left);
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
                1
              /  \
             2    2
            / \  / \
           3  4 4   3
     */

    private static void test() {
        Node node1 = new Node(3, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(4, null, null);
        Node node4 = new Node(3, null, null);
        Node node5 = new Node(2, node1, node2);
        Node node6 = new Node(2, node3, node4);
        Node node7 = new Node(1, node5, node6);
        boolean result = treeSolution(node7);
        System.out.println("result = " + result);
//        assert result;
    }

    public static void main(String[] args) {
        test();
    }
}
