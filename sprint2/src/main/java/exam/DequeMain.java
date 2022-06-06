package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Passed ID 67605621
/**
 * -- ПРИНЦИП РАБОТЫ --
 * Дэк основан на круговом массиве. Хранятся ссылки на head/tail и рассчитывается size при добавлении/удалении элементов
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Исходя из работы методов по добавлению элемента в начало/конец, элемент будет добавлен только в том случае,
 * если в массиве есть свободное место. При доставании элемента из начала/конца, элемент будет возращен только,
 * если массив не пустой. Указатель head/tail могут двигаться по часовой или против часовой,
 * поэтому невозможно перезаписать или вытащить пустой элемент.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * все операции выполняются за O(1), так как выполняются только простейшие арифметические операции и операции присвоения
 */
interface Deque<V> {
    V pushBack(V value);
    V pushFront(V value);
    V popFront();
    V popBack();
}

class CircularArrayDeque<V> implements Deque<V> {
    private final V[] elements;
    private int head;
    private int tail;
    private int size;

    public CircularArrayDeque(int size) {
        elements = (V[]) new Object[size];
    }

    /**
     * Добавляет элемент в конец массива.
     * Если размер массива равен размеру текущих размещенных в нем элементов, то возвращаем null.
     * @param value
     * @return добавленное значение
     */
    @Override
    public V pushBack(V value) {
        if (isFull()) {
            return null;
        }

        elements[tail] = value;
        tail = (tail + 1) % elements.length;
        size++;

        return value;
    }

    /**
     * Добавляет элемент в начало массива.
     * Если размер массива равен размеру текущих размещенных в нем элементов, то возвращаем null.
     * @param value
     * @return добавленное значение
     */
    @Override
    public V pushFront(V value) {
        if (isFull()) {
            return null;
        }

        head = (elements.length + head - 1) % elements.length;
        elements[head] = value;
        size++;

        return value;
    }

    /**
     * Достает и убирает элемент из начала массива.
     * Если в текущем массиве нет элементов, то возвращаем null
     * @return элемент на позиции head
     */
    @Override
    public V popFront() {
        if (isEmpty()) {
            return null;
        }

        V element = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;

        return element;
    }

    /**
     * Достает и убирает элемент из конца массива.
     * Если в текущем массиве нет элементов, то возвращаем null
     * @return элемент на предыдущей позиции tail
     */
    @Override
    public V popBack() {
        if (isEmpty()) {
            return null;
        }

        tail = (elements.length + tail - 1) % elements.length;
        V element = elements[tail];
        elements[tail] = null;
        size--;

        return element;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == elements.length;
    }
}

public class DequeMain {

    private static final String PUSH_BACK = "push_back";
    private static final String POP_FRONT = "pop_front";
    private static final String POP_BACK = "pop_back";
    private static final String ERROR = "error";

    public static void main(String[] args) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int instructionNum = Integer.parseInt(reader.readLine());
            final int maxSize = Integer.parseInt(reader.readLine());
            final var deque = new CircularArrayDeque<Integer>(maxSize);
            for (int i = 0; i < instructionNum; i++) {
                final String instruction = reader.readLine();
                doInstruction(deque, instruction);
            }
        }
    }

    private static void doInstruction(Deque<Integer> deque, String instruction) {
        switch (instruction) {
            case POP_FRONT:
                final Integer popFront = deque.popFront();
                System.out.println(popFront != null ? popFront : ERROR);
                break;
            case POP_BACK:
                final Integer popBack = deque.popBack();
                System.out.println(popBack != null ? popBack : ERROR);
                break;
            default:
                final String[] complexInstruction = instruction.split(" ");
                final String push = complexInstruction[0];
                final int number = Integer.parseInt(complexInstruction[1]);
                final var result = push.equals(PUSH_BACK) ? deque.pushBack(number) : deque.pushFront(number);
                if (result == null) {
                    System.out.println(ERROR);
                }
                break;
        }
    }
}
