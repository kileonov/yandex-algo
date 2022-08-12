package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Passed ID 69651454
/**
 * -- ПРИНЦИП РАБОТЫ --
 * Нудно создать префиксное дерево для хранения всех шаблонов (words). Терминальные узлы будут помечаться boolean.
 * Теперь проходимся по тексту и для каждого символа пытаемся найти максимально возможно формируемое слово, для этого
 * нам как раз и понадобятся терминальные узлы. Для ускорения работы используем динамический массив, где
 * будут лежать результаты предыдущих проходов по символам текста. Результат будет лежать в последнем индексе дм
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Так как мы проходимся по индексам строки, то возможно 2 случая:
 *  1. если мы нашли слово, то пометим дм как true и будет стартовать следующий поиск с него. То есть с индекса
 *      который уже ближе к концу строки
 *  2. если такого слова нет, то мы дойдем до конца без дальнейших проверок, так как минимум одно слово, мы уже не нашли
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Time:
 *  O(W_L) - создание префиксного дерева, где W_L - длина всех символов введенных слов
 *  O(n^2) - прохождение по всем символам текста
 *  Итог: O(W_L) + O(n^2) = O(n^2)
 * Space:
 *  O(W_L) - хранение префиксного дерева, где W_L - длина всех символов введенных слов
 *  O(n) - динамический массив для хранения результатов предыдущих вычислений
 *  Итог: O(W_L) + O(n) = O(W_L) + O(n)
 */
public class CheatSheet {

    static class Node {
        char value;
        Node[] next;
        boolean isTerminal;

        public Node(char value) {
            this(value, new Node[26], false);
        }

        public Node(char value, Node[] next, boolean isTerminal) {
            this.value = value;
            this.next = next;
            this.isTerminal = isTerminal;
        }
    }

    private static boolean containsAllWords(String str, Node trie) {
        final boolean[] dp = new boolean[str.length() + 1];
        dp[0] = true;

        for (int i = 0; i < str.length(); i++) {
            Node node = trie;
            if (dp[i]) {
                for (int j = i; j <= str.length(); j++) {
                    if (node.isTerminal) {
                        dp[j] = true;
                    }
                    if (j == str.length() || node.next[getArrayIdx(str, j)] == null) {
                        break;
                    }
                    node = node.next[getArrayIdx(str, j)];
                }
            }
        }

        return dp[str.length()];
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String str = reader.readLine();
            final int n = Integer.parseInt(reader.readLine());
            final Node trie = constructTrie(n, reader);

            final boolean result = containsAllWords(str, trie);
            System.out.println(result ? "YES" : "NO");
        }
    }

    private static Node constructTrie(int n, BufferedReader reader) throws IOException {
        final Node root = new Node(' ');
        for (int i = 0; i < n; i++) {
            final String word = reader.readLine();
            Node node = root;
            for (int j = 0; j < word.length(); j++) {
                final int charIdx = getArrayIdx(word, j);
                node.next[charIdx] = node.next[charIdx] != null ? node.next[charIdx] : new Node(word.charAt(j));
                node = node.next[charIdx];
            }
            node.isTerminal = true;
        }

        return root;
    }

    private static int getArrayIdx(String str, int idx) {
        return str.charAt(idx) - 'a';
    }
}
