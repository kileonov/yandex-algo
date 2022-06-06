package i;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

interface FixedSizeQueue<V> {
    V push(V element);
    V pop();
    V peek();
    int size();
}

class FixedSizeQueueOnArray<V> implements FixedSizeQueue<V> {
    private V[] elements;
    private int head;
    private int tail;
    private int size;

    public FixedSizeQueueOnArray(int size) {
        elements = (V[]) new Object[size];
        head = tail = size = 0;
    }

    @Override
    public V push(V element) {
        if (elements.length == size) return null;
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
        return element;
    }

    @Override
    public V pop() {
        if (size == 0) return null;
        V element = peek();
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return element;
    }

    @Override
    public V peek() {
        return elements[head];
    }

    @Override
    public int size() {
        return size;
    }
}

public class FixedQueue {
    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            int instructionNum = Integer.parseInt(reader.readLine());
            int maxSize = Integer.parseInt(reader.readLine());
            var queue = new FixedSizeQueueOnArray<Integer>(maxSize);
            for (int i = 0; i < instructionNum; i++) {
                String instruction = reader.readLine();
                doInstruction(queue, instruction);
            }
        }
    }

    private static void doInstruction(FixedSizeQueue<Integer> queue, String instruction) {
        switch (instruction) {
            case PEEK:
                var peek = queue.peek();
                System.out.println(peek != null ? peek : NONE);
                break;
            case POP:
                var pop = queue.pop();
                System.out.println(pop != null ? pop : NONE);
                break;
            case SIZE:
                var size = queue.size();
                System.out.println(size);
                break;
            default:
                String[] complexInstruction = instruction.split(" ");
                String push = complexInstruction[0];
                if (!push.equals(PUSH)) throw new IllegalArgumentException("Instruction is not supported: " + push);
                int number = Integer.parseInt(complexInstruction[1]);
                var pushedElement = queue.push(number);
                if (pushedElement == null) System.out.println(ERROR);
                break;
        }
    }

    private static final String POP = "pop";
    private static final String PEEK = "peek";
    private static final String PUSH = "push";
    private static final String SIZE = "size";
    private static final String NONE = "None";
    private static final String ERROR = "error";
}
