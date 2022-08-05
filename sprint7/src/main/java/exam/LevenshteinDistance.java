package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Passed ID 69585667
/**
 * -- ПРИНЦИП РАБОТЫ --
 * При работе у нас есть 3 операции: удалить/добавить/изменить символ.
 * Использовал рекуррентную формулу Вагнера — Фишера, так как сам не смог прийти к ней =(
 * Считываем 2 строки A, B. Формируем двумерный динамический массив и заполняем dp:
 *  1. dp[0][0] = 0
 *  2. dp[0][1..A.length] = 1..A.length
 *  3. dp[1..B.length][0] = 1..B.length
 *  4. нужно найти минимум между соседними элементами на позициях
 *      4.1 dp[i][j - 1] + 1 - соседний левый
 *      4.1 dp[i - 1][j] + 1 - соседний снизу
 *      4.1 dp[i - 1][j - 1] + VAL - соседний снизу слева, где VAL - если A[i] != B[i], то 1, иначе 0
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Time:
 *  O(A.length * B.length) - проход по dp
 * Space:
 *  O(A.length * B.length) - хранение dp
 */
public class LevenshteinDistance {

    private static int calculateLevenshteinDistance(String s, String t) {
        final int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= t.length(); i++) {
            dp[0][i] = i;
        }

        int cost;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                cost = s.charAt(i - 1) == t.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(
                    dp[i - 1][j] + 1,
                    Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + cost)
                );
            }
        }

        return dp[s.length()][t.length()];
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String s = reader.readLine();
            final String t = reader.readLine();

            final int result = calculateLevenshteinDistance(s, t);
            System.out.println(result);
        }
    }
}
