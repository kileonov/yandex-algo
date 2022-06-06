package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Passed ID 68804493
/**
 * -- ПРИНЦИП РАБОТЫ --
 * Создаем массив - это будут наши бакеты. Каждый раз при работе с массивом, нужно считать хэш ключа на входе
 * и работать с найденным бакетом по формуле: hash(key) % size. Могут возникнуть коллизии, для их устранения
 * используется вспомогательная структура - связной однонаправленный список. Если в таком бакете уже что-то лежит,
 * то нужно пройтись по каждому элементу связного списка и сравнить по равенству ключа. Удаление и поиск строится по
 * такому же принципу
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Хэш ключа - консистентный - следовательно элемент с таким же ключом всегда будет попадать в один и тот же бакет.
 * Могут возникать коллизии, но они решаются проходом по однонаправленному связному списку и явным сравнением ключей.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Time: O(1); Space: O(N).
 */
public class HashTableMain {

    private final static String GET = "get";
    private final static String PUT = "put";
    private final static String DELETE = "delete";
    private final static int Q = 1000000007;
    private final static int R = Integer.MAX_VALUE;

    public static class Node {
        HashTableMain.KeyVal data;
        Node next;

        public Node(HashTableMain.KeyVal data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" + data + ", next=" + next + '}';
        }
    }

    public static class KeyVal {
        int key;
        int value;

        public KeyVal(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "key = " + key + " value=" + value;
        }
    }

    public static class HashTable {
        private final int size = (2 << 16) - 1;
        private final Node[] values;

        public HashTable() {
            values = new Node[size];
        }

        void put(int key, int value) {
            final int bucket = getBucket(key);

            Node currentBucket = values[bucket];
            Node prevNode = null;
            while (currentBucket != null && currentBucket.data.key != key) {
                prevNode = currentBucket;
                currentBucket = currentBucket.next;
            }

            final var nodeToInsert = new Node(new KeyVal(key, value), currentBucket != null ? currentBucket.next : null);
            if (prevNode == null) {
                values[bucket] = nodeToInsert;
            } else {
                prevNode.next = nodeToInsert;
            }

            if (currentBucket != null) {
                currentBucket.next = null;
            }
        }

        Integer get(int key) {
            final int bucket = getBucket(key);

            Node currentBucket = values[bucket];
            while (currentBucket != null && currentBucket.data.key != key) {
                currentBucket = currentBucket.next;
            }
            return currentBucket != null ? currentBucket.data.value : null;
        }

        Integer delete(int key) {
            final int bucket = getBucket(key);

            Node currentBucket = values[bucket];
            Node prevNode = null;
            while (currentBucket != null && currentBucket.data.key != key) {
                prevNode = currentBucket;
                currentBucket = currentBucket.next;
            }

            final var nextNode = currentBucket != null ? currentBucket.next : null;
            if (prevNode == null) {
                values[bucket] = nextNode;
            } else {
                prevNode.next = nextNode;
            }

            if (currentBucket != null) {
                currentBucket.next = null;
                return currentBucket.data.value;
            } else {
                return null;
            }
        }

        int hashCode(int element) {
            // игрался с разными хешами, по идее можно даже просто возвращать element
            final long result = (long) element * Q;
            return (int) (result % R);
        }

        int getBucket(int key) {
            return hashCode(key) % size;
        }
    }


    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int n = Integer.parseInt(reader.readLine());

            final StringBuilder result = new StringBuilder();
            final HashTable hashTable = new HashTable();
            for (int i = 0; i < n; i++) {
                doInstruction(hashTable, reader.readLine(), result);
            }
            System.out.println(result);
        }
    }

    private static void doInstruction(HashTable hashTable, String instruction, StringBuilder result) {
        final String[] operands = instruction.split(" ");

        switch (operands[0]) {
            case GET:
                final Integer fetchedValue = hashTable.get(Integer.parseInt(operands[1]));
                result.append(fetchedValue != null ? fetchedValue : "None").append(System.lineSeparator());
                break;
            case PUT:
                hashTable.put(Integer.parseInt(operands[1]), Integer.parseInt(operands[2]));
                break;
            case DELETE:
                final Integer deletedValue = hashTable.delete(Integer.parseInt(operands[1]));
                result.append(deletedValue != null ? deletedValue : "None").append(System.lineSeparator());
                break;
            default:
                throw new UnsupportedOperationException("Operation " + operands[0] + " is not supported");
        }
    }
}
