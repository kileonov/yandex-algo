package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.function.BiFunction;

// Passed ID 67643630
/**
 * -- ПРИНЦИП РАБОТЫ --
 * Калькулятор основан на стеке, где числа постоянно записываются в стек. Если попадается арифметическая операция,
 * достаются последние помещенные 2 числа (LIFO) и результат операции снова кладется в стек.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Так как в задании гарантируется корректность входных данных, то кол-во переданных чисел будет равно кол-ву
 * арифметических операций + 1. Значит в конце всех вычислений в стеке останется всего лишь один элемент. Это и будет
 * результатом наших вычислений.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * все операции выполняются за O(1), так как выполняются только простейшие арифметические операции и операции присвоения
 */
class Node<V> {
    V value;
    Node<V> next;

    public Node(V value, Node<V> next) {
        this.value = value;
        this.next = next;
    }
}

interface Stack<V> {
    V push(V value);
    V pop();
    int size();
    boolean isEmpty();
}

class StackOnNode<V> implements Stack<V> {
    private Node<V> head;
    private int size;

    @Override
    public V push(V value) {
        head = new Node<>(value, head);
        size++;
        return value;
    }

    @Override
    public V pop() {
        if (isEmpty()) return null;

        var element = head;
        head = head.next;
        size--;

        return element.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}

public class CalculatorMain {

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";

    private static final Map<String, BiFunction<Integer, Integer, Integer>> instructionToOperation = Map.of(
        PLUS, Integer::sum,
        MINUS, (Integer leftOperand, Integer rightOperand) -> leftOperand - rightOperand,
        MULTIPLY, (Integer leftOperand, Integer rightOperand) -> leftOperand * rightOperand,
        DIVIDE, Math::floorDiv
    );

    private static void doCalculation(Stack<Integer> numbers, BiFunction<Integer, Integer, Integer> operation) {
        final var rightOperand = numbers.pop();
        final var leftOperand = numbers.pop();
        numbers.push(operation.apply(leftOperand, rightOperand));
    }

    private static int calculate(String[] instructions) {
        final Stack<Integer> numbers = new StackOnNode<>();

        for (String instruction : instructions) {
            if (instructionToOperation.containsKey(instruction)) {
                doCalculation(numbers, instructionToOperation.get(instruction));
            } else {
                numbers.push(Integer.parseInt(instruction));
            }
        }

        return numbers.pop();
    }

    public static void main(String[] args) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            final var instructions = reader.readLine().split(" ");
            final int result = calculate(instructions);
            System.out.println(result);
        }
    }
}
