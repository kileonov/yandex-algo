package exam;

public class Solution {

    public static Node remove(Node root, int key) {
        Relation deleteRelation = findDeleteElWithParent(null, root, key, true);
        if (deleteRelation.child == null) return root;

        if (deleteRelation.child.getLeft() == null && deleteRelation.child.getRight() == null) {
            if (deleteRelation.parent == null) {
                root = null;
            } else if (deleteRelation.isLeft) {
                deleteRelation.parent.setLeft(null);
            } else {
                deleteRelation.parent.setRight(null);
            }
        } else if (deleteRelation.child.getLeft() == null) {
            if (deleteRelation.child == root) {
                root = deleteRelation.child.getRight();
            } else if (deleteRelation.isLeft) {
                deleteRelation.parent.setLeft(deleteRelation.child.getRight());
            } else {
                deleteRelation.parent.setRight(deleteRelation.child.getRight());
            }
        } else if (deleteRelation.child.getRight() == null) {
            if (deleteRelation.child == root) {
                root = deleteRelation.child.getLeft();
            } else if (deleteRelation.isLeft) {
                deleteRelation.parent.setLeft(deleteRelation.child.getLeft());
            } else {
                deleteRelation.parent.setRight(deleteRelation.child.getLeft());
            }
        } else {
            Relation replaceRelation = findReplacedElWithParent(deleteRelation.child, deleteRelation.child.getRight());
            if (replaceRelation.parent != deleteRelation.child) {
                replaceRelation.parent.setLeft(replaceRelation.child.getRight());
                replaceRelation.child.setRight(null);
            }

            if (deleteRelation.child == root) {
                root = replaceRelation.child;
            } else if (deleteRelation.isLeft) {
                deleteRelation.parent.setLeft(replaceRelation.child);
            } else {
                deleteRelation.parent.setRight(replaceRelation.child);
            }

            if (replaceRelation.child != deleteRelation.child.getLeft()) {
                replaceRelation.child.setLeft(deleteRelation.child.getLeft());
            }
            if (replaceRelation.child != deleteRelation.child.getRight()) {
                replaceRelation.child.setRight(deleteRelation.child.getRight());
            }

            deleteRelation.child.setLeft(null);
            deleteRelation.child.setRight(null);
        }

        return root;
    }

    public static Relation findDeleteElWithParent(Node parent, Node child, int key, boolean isLeft) {
        if (child == null) return new Relation(parent, null, isLeft);

        if (child.getValue() == key) {
            return new Relation(parent, child, isLeft);
        } else if (child.getValue() > key) {
            return findDeleteElWithParent(child, child.getLeft(), key, true);
        } else {
            return findDeleteElWithParent(child, child.getRight(), key, false);
        }
    }

    public static Relation findReplacedElWithParent(Node parent, Node child) {
        if (child == null) return new Relation(parent, null);

        if (child.getLeft() == null) return new Relation(parent, child);

        return findReplacedElWithParent(child, child.getLeft());
    }

    private static class Relation {
        private Node parent;
        private Node child;
        private boolean isLeft;

        public Relation(Node parent, Node child) {
            this.parent = parent;
            this.child = child;
        }

        public Relation(Node parent, Node child, boolean isLeft) {
            this.parent = parent;
            this.child = child;
            this.isLeft = isLeft;
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

        Node node1 = new Node(null, null, 1);
        Node node2 = new Node(null, null, 3);
        Node node3 = new Node(node1, node2, 2);
        Node node4 = new Node(null, null, 5);
        Node node5 = new Node(null, null, 7);
        Node node6 = new Node(node4, node5, 6);
        Node node7 = new Node(node3, node6, 4);
        Node newHead = remove(node7, 4);

//        Node node1 = new Node(null, null, 7);
//        Node node2 = new Node(null, node1, 5);
//        Node node3 = new Node(node2, null, 10);
//        Node remove = remove(node3, 10);

//        Node node1 = new Node(null, null, 2);
//        Node node2 = new Node(null, node1, 1);
//        Node remove = remove(node2, 1);
    }

    public static void main(String[] args) {
        test();
    }
}
