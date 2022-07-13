package exam;

public class Solution {

    public static Node remove(Node root, int key) {
        final Relation deleteRelation = findDeleteElWithParent(null, root, key, true);
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
            final Relation replaceRelation = findReplacedElWithParent(deleteRelation.child, deleteRelation.child.getRight());
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
        private final Node parent;
        private final Node child;
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
    }
}
