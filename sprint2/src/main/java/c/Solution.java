package c;

class Node<V> {
    public V value;
    public Node<V> next;

    public Node(V value, Node<V> next) {
        this.value = value;
        this.next = next;
    }
}

public class Solution {
    public static Node<String> solution(Node<String> head, int idx) {
        if (idx == 0) {
            head = head.next;
            return head;
        }

        Node<String> previous = head;
        while (--idx != 0) {
            previous = previous.next;
        }
        previous.next = previous.next.next;

        return head;
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        Node<String> newHead = solution(node0, 3);
        while (newHead != null) {
            System.out.println(newHead.value);
            newHead = newHead.next;
        }
        // result is : node0 -> node2 -> node3
    }
}

