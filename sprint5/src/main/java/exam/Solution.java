package exam;

public class Solution {

    public static Node remove(Node root, int key) {
        Relation deleteRelation = findDeleteElWithParent(null, root, key);
        if (deleteRelation.current == null) return root;

        if (deleteRelation.current.getLeft() == null && deleteRelation.current.getRight() == null) {
            if (deleteRelation.parent == null) return null;

            if (deleteRelation.parent.getLeft() == deleteRelation.current) {
                deleteRelation.parent.setLeft(null);
            } else {
                deleteRelation.parent.setRight(null);
            }
            return root;
        }

        // parent null|notnull
        // current notnull
        Relation replaceRelation = findReplacedElWithParent(null, deleteRelation.current.getRight());
        if (replaceRelation.parent != null) {
            replaceRelation.parent.setLeft(null);
        }
        if (replaceRelation.current == null) {
            if (deleteRelation.parent != null) {
                if (deleteRelation.parent.getLeft() == deleteRelation.current) {
                    deleteRelation.parent.setLeft(deleteRelation.current.getLeft());
                } else {
                    deleteRelation.parent.setRight(deleteRelation.current.getLeft());
                }
            } else {
                Node temp = deleteRelation.current.getLeft();
                deleteRelation.current.setLeft(null);
                return temp;
            }
        } else {
            Node temp = replaceRelation.current.getRight();
            if (deleteRelation.parent != null) {
                if (deleteRelation.parent.getLeft() == deleteRelation.current) {
                    deleteRelation.parent.setLeft(replaceRelation.current);
                } else {
                    deleteRelation.parent.setRight(replaceRelation.current);
                }
                replaceRelation.current.setLeft(deleteRelation.current.getLeft());
                replaceRelation.current.setRight(null);
            } else {
                if (replaceRelation.current != deleteRelation.current.getLeft()) {
                    replaceRelation.current.setLeft(deleteRelation.current.getLeft());
                }
                if (replaceRelation.current != deleteRelation.current.getRight()) {
                    replaceRelation.current.setRight(deleteRelation.current.getRight());
                }
            }
            if (temp != null && replaceRelation.parent != null) {
                replaceRelation.parent.setLeft(temp);
            }
        }
        deleteRelation.current.setLeft(null);
        deleteRelation.current.setRight(null);

        if (deleteRelation.parent == null) {
            return replaceRelation.current;
        }

        return root;
    }

    public static Relation findDeleteElWithParent(Node parent, Node current, int key) {
        if (current == null) return new Relation(parent, null);

        if (current.getValue() == key) {
            return new Relation(parent, current);
        } else if (current.getValue() > key) {
            return findDeleteElWithParent(current, current.getLeft(), key);
        } else {
            return findDeleteElWithParent(current, current.getRight(), key);
        }
    }

    public static Relation findReplacedElWithParent(Node parent, Node current) {
        if (current == null) return new Relation(parent, null);

        if (current.getLeft() == null) return new Relation(parent, current);

        return findReplacedElWithParent(current, current.getLeft());
    }

    private static class Relation {
        private Node parent;
        private Node current;

        public Relation(Node parent, Node current) {
            this.parent = parent;
            this.current = current;
        }
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

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    /*
    7
    1 4 2 3
    2 2 4 5
    3 6 6 7
    4 1 -1 -1
    5 3 -1 -1
    6 5 -1 -1
    7 7 -1 -1
    6

            4
           / \
          2   6
         / \  / \
        1   3 5  7

        6
     */

    private static void test() {
//        Node node1 = new Node(null, null, 1);
//        Node node2 = new Node(null, null, 3);
//        Node node3 = new Node(node1, node2, 2);
//        Node node4 = new Node(null, null, 5);
//        Node node5 = new Node(null, null, 7);
//        Node node6 = new Node(node4, node5, 6);
//        Node node7 = new Node(node3, node6, 4);
//        System.out.println("node7 = " + node7);
//        Node newHead = remove(node7, 6);
//        System.out.println("newHead = " + newHead);
//        System.out.println("newHead = " + newHead.getRight().value);

//        Node node1 = new Node(null, null, 2);
//        Node node2 = new Node(node1, null, 3);
//        Node node3 = new Node(null, node2, 1);
//        Node node4 = new Node(null, null, 6);
//        Node node5 = new Node(node4, null, 8);
//        Node node6 = new Node(node5, null, 10);
//        Node node7 = new Node(node3, node6, 5);
//        Node newHead = remove(node7, 10);

//        Node node1 = new Node(null, null, 1);
//        Node node2 = new Node(null, null, 3);
//        Node node3 = new Node(node1, node2, 2);
//        Node node4 = new Node(null, null, 5);
//        Node node5 = new Node(null, null, 7);
//        Node node6 = new Node(node4, node5, 6);
//        Node node7 = new Node(node3, node6, 4);
//        Node newHead = remove(node7, 4);

//        Node node1 = new Node(null, null, 7);
//        Node node2 = new Node(null, node1, 5);
//        Node node3 = new Node(node2, null, 10);
//        remove(node3, 10);

        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(null, node1, 1);
        remove(node2, 1);
    }

    public static void main(String[] args) {
        test();
    }
}
