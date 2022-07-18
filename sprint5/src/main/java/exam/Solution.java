package exam;

// Passed ID 69425119
/**
 * -- ПРИНЦИП РАБОТЫ --
 * При удалении элемента из BST возможно 3 варианта:
 *  1. Элемент не имеет детей. Если этот элемент - корень, возвращаем null, иначе зануляем этот элемент у родителя
 *  2. Элемент имеет одного потомка. Если этот элемент - корень, присвоим корню следующий элемент левый/правый,
 *      иначе присвоим родителю найденного элемента потомка найденного элемент левый/правый
 *  3. Элемент имеет двух потомков. Находим самый левый элемент от правого потомка найденного элемента - самый
 *      большой слева, но меньше всех справа MLE. Если у него есть потомки справа, присвоим их родителю слева этого элемента.
 *      Теперь если найденный элемент - корень, заменим его на MLE, иначе родителю присвоим MLE левый/правый. И осталось
 *      у элемента MLE перебросить ссылки на потомков найденного элемента
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * По шагам сверху:
 *  1. Мы просто удаляем лист - не теряем свойств BST
 *  2. Мы перебрасываем ссылку на следующего потомка найденного элемента - не теряем свойст BST
 *  3. Когда открепляем правого потомка от MLE и цепляем его к родителю MLE слева, то данное дерево тоже BST, так как
 *      любой потомок MLE - точно меньше чем родитель MLE. Теперь заменяем удаленный элемент на MLE - дерево тоже
 *      остается BST, так как MLE - точно больше всех левых потомков и меньше всех правых потомков удаляемого элемента.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Time:
 *  O(h) - если не нашли такой элемент, h - высота дерева
 *  O(h) + O(1) - если найденный элемент - лист, h - высота дерева
 *  O(h) + O(1) - если найденный элемент имеет только одного потомка, h - высота дерева
 *  O(h) + O(h) + O(1) - если найденный элемент имеет двух потомков, h - высота дерева
 *  Итог: O(h) + O(h) + O(1) + O(h) + O(1) + O(h) + O(h) + O(1) = O(h)
 * Space:
 *  O(n) - сама структура BST для хранения элементов
 *  O(h) - операция удаления, где h - высота дерева
 *  Итог: O(n) + O(h) = O(n) + O(h)
 */
public class Solution {

    public static Node remove(Node root, int key) {
        final Relation deleteRelation = findDeleteElWithParent(null, root, key, true);
        if (deleteRelation.child == null) {
            return root;
        }

        if (deleteRelation.child.getLeft() == null && deleteRelation.child.getRight() == null) {
            if (deleteRelation.parent == null) {
                root = null;
            } else if (deleteRelation.isLeft) {
                deleteRelation.parent.setLeft(null);
            } else {
                deleteRelation.parent.setRight(null);
            }
        } else if (deleteRelation.child.getLeft() == null) {
            root = removeNodeRelations(root, deleteRelation, deleteRelation.child.getRight());
        } else if (deleteRelation.child.getRight() == null) {
            root = removeNodeRelations(root, deleteRelation, deleteRelation.child.getLeft());
        } else {
            final Relation replaceRelation = findReplacedElWithParent(deleteRelation.child, deleteRelation.child.getRight());
            if (replaceRelation.parent != deleteRelation.child) {
                replaceRelation.parent.setLeft(replaceRelation.child.getRight());
                replaceRelation.child.setRight(null);
            }
            root = removeNodeRelations(root, deleteRelation, replaceRelation.child);

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

    private static Node removeNodeRelations(Node root, Relation nodeToDelete, Node child) {
        if (nodeToDelete.child == root) {
            root = child;
        } else if (nodeToDelete.isLeft) {
            nodeToDelete.parent.setLeft(child);
        } else {
            nodeToDelete.parent.setRight(child);
        }
        return root;
    }

    public static Relation findDeleteElWithParent(Node parent, Node child, int key, boolean isLeft) {
        if (child == null) {
            return new Relation(parent, null, isLeft);
        }

        if (child.getValue() == key) {
            return new Relation(parent, child, isLeft);
        } else if (child.getValue() > key) {
            return findDeleteElWithParent(child, child.getLeft(), key, true);
        } else {
            return findDeleteElWithParent(child, child.getRight(), key, false);
        }
    }

    public static Relation findReplacedElWithParent(Node parent, Node child) {
        if (child == null) {
            return new Relation(parent, null);
        }

        if (child.getLeft() == null) {
            return new Relation(parent, child);
        }

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
