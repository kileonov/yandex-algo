package h;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GlobalReplacement {

    private static String replaceStr(String str, String template, String replacement) {
        List<Integer> result = new ArrayList<>();
        String combined = template + '#' + str;

        int[] p = new int[template.length()];
        int pPrev = 0;

        for (int i = 1; i < combined.length(); i++) {
            int k = pPrev;
            while (k > 0 && combined.charAt(k) != combined.charAt(i)) {
                k = p[k - 1];
            }
            if (combined.charAt(k) == combined.charAt(i)) {
                k++;
            }

            if (i < template.length()) {
                p[i] = k;
            }

            pPrev = k;
            if (k == template.length()) {
                result.add(i - 2 * template.length());
            }
        }

        StringBuilder writer = new StringBuilder();
        int prevIdx = 0;
        for (Integer idx : result) {
            writer.append(str, prevIdx, idx).append(replacement);
            prevIdx = idx + template.length();
        }
        if (prevIdx < str.length()) {
            writer.append(str, prevIdx, str.length());
        }

        return writer.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = reader.readLine();
            String template = reader.readLine();
            String replacement = reader.readLine();

            String result = replaceStr(str, template, replacement);
            System.out.println(result);
        }
    }
}
