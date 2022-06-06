package tasks.h;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinaryNumSum {
    private static String sumOfBinaries(String a, String b) {
        var result = new StringBuilder();
        int maxLength = Math.max(a.length(), b.length());
        var nextDigitSum = 0;
        for (int i = 0; i < maxLength; i++) {
            var aIdx = a.length() - 1 - i;
            var bIdx = b.length() - 1 - i;

            var currentDigitSum = 0;
            if (aIdx >= 0) currentDigitSum += Character.getNumericValue(a.charAt(aIdx));
            if (bIdx >= 0) currentDigitSum += Character.getNumericValue(b.charAt(bIdx));
            currentDigitSum += nextDigitSum;

            nextDigitSum = currentDigitSum / 2;
            currentDigitSum %= 2;
            result.append(currentDigitSum);
        }
        if (nextDigitSum == 1) result.append(nextDigitSum);

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String a = reader.readLine();
            String b = reader.readLine();
            System.out.println(sumOfBinaries(a, b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
