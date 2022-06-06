package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Substring {

    private static int calculateUniqueMaxSubStrSizeNew(String str) {
        int max = 0;
        final var current = new StringBuilder();
        Map<Character, Integer> symbolToIdx = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            final char ch = str.charAt(i);
            if (symbolToIdx.containsKey(ch)) {
                if (max < current.length()) max = current.length();

                current.delete(0, symbolToIdx.get(ch) + 1);
                final char[] recalculateUniqueIdx = current.toString().toCharArray();
                symbolToIdx = new HashMap<>();
                for (int j = 0; j < recalculateUniqueIdx.length; j++) {
                    symbolToIdx.put(recalculateUniqueIdx[j], j);
                }

            }
            current.append(ch);
            symbolToIdx.put(ch, current.length() - 1);
        }
        if (max < current.length()) max = current.length();

        return max;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = reader.readLine();
            int result = calculateUniqueMaxSubStrSizeNew(str);
            System.out.println(result);
        }
    }
}
