package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// Passed ID 69658343
/**
 * -- ПРИНЦИП РАБОТЫ --
 * Нужно распаковать строку:
 *  1. Если символ - число, то добавляем в стек чисел
 *  2. Если символ - [, то добавляем пустую строку в стек строк
 *  3. Если символ - ], то считаем:
 *      3.1 Если стек строк имеет длину один - это крайний случай, тогда в финальный результат дописываем число * строка
 *      3.2 Иначе в предыдущий результат дописываем число * строка
 *  4. Если стек пустой, то в финальный результат символ
 *  5. Иначе - это символ, который конкатенируем с последним элементов в стеке строк
 * Теперь считаем максимальный префикс. Тут довольно просто, воспользуемся жадным подходом и будем считать, что
 * макс префикс = первой строке. А потом просто сравниваем с последующими распакованными строками и уменьшаем размер
 * при несовпадении.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Распаковка строки имеет только 5 возможных случаев по условию, которые рассмотрели выше.
 * Расчет макс префикса - он должен быть общим у всех строк, поэтому если он отличается хотя бы у двух, то он отличается
 * и у остальных.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Time:
 *  O(n * (m + m)) - распаковка + проверка префикса, где n - число строк, m - макс длина строки
 *  Итог: O(n * (m + m)) = O(n * m)
 * Space:
 *  O(m) - стек символов, где m - макс длина строки
 *  O(m) - стек операндов, где m - макс длина строки
 *  O(z) - результат распакованной строки, где z - макс длина строки распакованной строки
 *  O(z) - результат макс префикса, где z - макс длина строки распакованной строки
 *  Итог: O(m) + O(m) + O(z) + O(z) = O(m) + O(z)
 */
public class PackedPrefix {

    private static String unzip(String line) {
        final Stack<StringBuilder> strs = new Stack<>();
        final Stack<Integer> operands = new Stack<>();
        final StringBuilder result = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            final char ch = line.charAt(i);

            if (Character.isDigit(ch)) {
                operands.push(Character.getNumericValue(ch));
            } else if (ch == '[') {
                strs.push(new StringBuilder());
            } else if (ch == ']') {
                if (strs.size() == 1) {
                    result.append(String.valueOf(strs.pop()).repeat(operands.pop()));
                } else {
                    final StringBuilder current = strs.pop();
                    strs.push(strs.pop().append(String.valueOf(current).repeat(operands.pop())));
                }
            } else if (strs.isEmpty()) {
                result.append(ch);
            } else {
                strs.push(strs.pop().append(ch));
            }
        }

        return result.toString();
    }

    private static String calculateMaxPrefix(BufferedReader reader, int n) throws IOException {
        StringBuilder maxPrefix = new StringBuilder();
        maxPrefix.append(unzip(reader.readLine()));

        for (int i = 0; i < n - 1; i++) {
            final String nextUnzippedStr = unzip(reader.readLine());

            for (int j = 0; j < Math.min(maxPrefix.length(), nextUnzippedStr.length()); j++) {
                if (maxPrefix.charAt(j) != nextUnzippedStr.charAt(j)) {
                    maxPrefix = new StringBuilder(maxPrefix.subSequence(0, j));
                    break;
                }
            }
        }

        return maxPrefix.toString();
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int n = Integer.parseInt(reader.readLine());

            final String result = calculateMaxPrefix(reader, n);
            System.out.println(result);
        }
    }
}
