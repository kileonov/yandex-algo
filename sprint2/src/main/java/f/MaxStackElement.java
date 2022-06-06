package f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

interface Stack {
    void push(int value);
    int pop();
    int getMax();
}

class StackOnArray implements Stack {
    private int[] stack;
    private int idx;
    private int[] maxElements;

    public StackOnArray() {
        stack = new int[16];
        maxElements = new int[16];
        Arrays.fill(maxElements, Integer.MIN_VALUE);
    }

    @Override
    public void push(int num) {
        if (idx == stack.length - 1) {
            int[] copy = new int[stack.length * 2];
            System.arraycopy(stack, 0, copy, 0, stack.length);
            stack = copy;
        }

        if (idx == maxElements.length - 1) {
            int[] copy = new int[maxElements.length * 2];
            System.arraycopy(maxElements, 0, copy, 0, maxElements.length);
            maxElements = copy;
        }

        var newIdx = idx + 1;
        maxElements[newIdx] = Math.max(num, maxElements[idx]);
        stack[newIdx] = num;
        idx = newIdx;
    }

    @Override
    public int pop() {
        if (idx == 0) return Integer.MIN_VALUE;

        maxElements[idx] = Integer.MIN_VALUE;
        int el = stack[idx];
        stack[idx] = 0;
        idx--;

        return el;
    }

    @Override
    public int getMax() {
        return maxElements[idx];
    }
}

class Node {
    public int value;
    public Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}

class StackOnList implements Stack {
    private Node valueHead;
    private Node maxHead;

    @Override
    public void push(int value) {
        if (maxHead == null) {
            maxHead = new Node(value, null);
        } else {
            maxHead = new Node(Math.max(maxHead.value, value), maxHead);
        }
        valueHead = new Node(value, valueHead);
    }

    @Override
    public int pop() {
        if (maxHead != null) maxHead = maxHead.next;
        if (valueHead == null) return Integer.MIN_VALUE;
        var node = valueHead;
        valueHead = valueHead.next;
        return node.value;
    }

    @Override
    public int getMax() {
        return maxHead != null ? maxHead.value : Integer.MIN_VALUE;
    }
}

public class MaxStackElement {

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());

//            var stack = new StackOnList();
            var stack = new StackOnArray();
            for (int i = 0; i < n; i++) {
                doInstruction(stack, reader.readLine());
            }
        }
    }

    private static void doInstruction(Stack stackOnList, String instruction) {
        switch (instruction) {
            case GET_MAX:
                var max = stackOnList.getMax();
                System.out.println(max == Integer.MIN_VALUE ? NONE : max);
                break;
            case POP:
                int pop = stackOnList.pop();
                if (pop == Integer.MIN_VALUE) System.out.println(ERROR);
//                System.out.println(pop == Integer.MIN_VALUE ? ERROR : pop);
                break;
            default:
                String[] complexInstruction = instruction.split(" ");
                String push = complexInstruction[0];
                if (!push.equals(PUSH)) throw new IllegalArgumentException("Instruction is not supported: " + push);
                int number = Integer.parseInt(complexInstruction[1]);
                stackOnList.push(number);
                break;
        }
    }

    private static final String GET_MAX = "get_max";
    private static final String POP = "pop";
    private static final String PUSH = "push";
    private static final String NONE = "None";
    private static final String ERROR = "error";
}
