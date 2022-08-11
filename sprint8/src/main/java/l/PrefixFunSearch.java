package l;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrefixFunSearch {

    private static int[] calculatePrefixFun(String str) {
        int[] p = new int[str.length()];

        for (int i = 1; i < str.length(); i++) {
            int k = p[i - 1];
            while (k > 0 && str.charAt(k) != str.charAt(i)) {
                k = p[k - 1];
            }
            if (str.charAt(k) == str.charAt(i)) {
                k++;
            }
            p[i] = k;
        }

        return p;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = reader.readLine();

            StringBuilder writer = new StringBuilder();
            int[] result = calculatePrefixFun(str);
            for (int num : result) {
                writer.append(num).append(' ');
            }
            System.out.println(writer);
        }
    }
}
