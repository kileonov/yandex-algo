package h;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

interface Stack<V> {
    void push(V value);
    V pop();
    boolean isEmpty();
}

class Node<V> {
    public V value;
    public Node<V> next;

    public Node(V value, Node<V> next) {
        this.value = value;
        this.next = next;
    }
}

class StackOnList<V> implements Stack<V> {
    private Node<V> head;

    @Override
    public void push(V value) {
        head = new Node<V>(value, head);
    }

    @Override
    public V pop() {
        if (head == null) return null;
        var node = head;
        head = head.next;
        return node.value;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}

public class ParenthesesOrder {

    private static final Map<Character, Character> LEFT_TO_RIGHT_PARENTHESES = Map.of('(', ')', '[', ']', '{', '}');
    private static final Map<Character, Character> RIGHT_TO_LEFT_PARENTHESES = Map.of(')', '(', ']', '[', '}', '{');

    private static boolean isCorrectBracketSeq(String parentheses) {
        var stack = new StackOnList<Character>();
        for (int i = 0; i < parentheses.length(); i++) {
            char parenthesis = parentheses.charAt(i);
            if (LEFT_TO_RIGHT_PARENTHESES.containsKey(parenthesis)) {
                stack.push(parenthesis);
            } else {
                Character top = stack.pop();
                if (top == null || !top.equals(RIGHT_TO_LEFT_PARENTHESES.get(parenthesis))) return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            String parentheses = reader.readLine();
            boolean result = isCorrectBracketSeq(parentheses);
            if (result) System.out.println("True");
            else System.out.println("False");
        }
    }
}
