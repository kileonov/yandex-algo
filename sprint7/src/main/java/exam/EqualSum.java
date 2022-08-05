package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Passed ID 69585667
/**
 * -- ПРИНЦИП РАБОТЫ --
 * Нужно посчитать половину суммы чисел, если она нечетная, то выдаем False. Иначе применяем
 * динамическое программирование. Создаем двумерный массив dp, в качестве строк будет диапазон от 0..(sum / 2),
 * в качестве столбцов будут элементы изначального массива (каждый элемент от 0..(array.lastIndex).
 * В dp будут храниться boolean значения со следующим рекуррентным соотношением:
 *  dp[i][j] = TRUE если выполняются условия:
 *      текущая сумма уже получена из предшествующего элемента dp[i][j - 1] = TRUE
 *      ИЛИ
 *      текущую сумму можно получить, если вычесть из текущей суммы рассматриваемый элемент dp[i - EL(j)][j - 1]
 *  иначе dp[i][j] = FALSE
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Половину суммы в dp[sum/2][j] = TRUE мы получим только тогда, когда будет существовать какое-то подмножество
 * множества текущих элементов дающих половины суммы, а значит другое подмножество тоже дает половину суммы
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Time:
 *  O(n * sum) - n - кол-во элементов, sum - искомая сумма
 * Space:
 *  O(n) - текущий массив
 *  O(n * sum) - динамический массив для хранения результатов предыдущих вычислений
 *  Итог: O(n) + O(n * sum) = O(n * sum)
 */
public class EqualSum {

    private static boolean hasEqualSum(int[] points) {
        int sum = 0;
        for (int point : points) {
            sum += point;
        }
        if (sum % 2 != 0) return false;

        final int halfSum = sum / 2;
        final boolean[][] dp = new boolean[halfSum + 1][points.length + 1];
        Arrays.fill(dp[0], true);
        for (int i = 1; i <= halfSum; i++) {
            for (int j = 1; j <= points.length; j++) {
                if (i - points[j - 1] >= 0) {
                    dp[i][j] = dp[i][j - 1] || dp[i - points[j - 1]][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[halfSum][points.length];
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int n = Integer.parseInt(reader.readLine());
            final int[] points = new int[n];
            final StringTokenizer tokens = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                points[i] = Integer.parseInt(tokens.nextToken());
            }
            final boolean result = hasEqualSum(points);
            System.out.println(result ? "True" : "False");
        }
    }
}
