package j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Node<V> {
    V data;
    Node<V> next;

    public Node(V data, Node<V> next) {
        this.data = data;
        this.next = next;
    }
}

interface QueueOnList<V> {
    V get();
    V put(V element);
    int size();
}

class QueueOnListImpl<V> implements QueueOnList<V> {
    private Node<V> head;
    private Node<V> tail;
    private int size;

    @Override
    public V get() {
        if (head == null) {
            return null;
        }
        var element = head;
        head = head.next;
        if (head == null) tail = null;
        size = size > 0 ? size - 1 : 0;
        return element.data;
    }

    @Override
    public V put(V element) {
        var node = new Node<V>(element, null);
        if (head == null && tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        size++;
        return element;
    }

    @Override
    public int size() {
        return size;
    }
}

public class QueueOnListMain {
    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            int instructionsNum = Integer.parseInt(reader.readLine());
            var queue = new QueueOnListImpl<Integer>();
            for (int i = 0; i < instructionsNum; i++) {
                String instruction = reader.readLine();
                doInstruction(queue, instruction);
            }
        }
    }

    private static void doInstruction(QueueOnList<Integer> queue, String instruction) {
        switch (instruction) {
            case GET:
                var get = queue.get();
                System.out.println(get != null ? get : ERROR);
                break;
            case SIZE:
                var size = queue.size();
                System.out.println(size);
                break;
            default:
                String[] complexInstruction = instruction.split(" ");
                String push = complexInstruction[0];
                if (!push.equals(PUT)) throw new IllegalArgumentException("Instruction is not supported: " + push);
                int number = Integer.parseInt(complexInstruction[1]);
                queue.put(number);
                break;
        }
    }

    private static final String GET = "get";
    private static final String PUT = "put";
    private static final String SIZE = "size";
    private static final String ERROR = "error";
}
