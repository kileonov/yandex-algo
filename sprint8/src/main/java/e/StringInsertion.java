package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class StringInsertion {

    private static String getInsertedStr(String s, Map<Integer, String> idxToStr) {
        StringBuilder writer = new StringBuilder();

        for (int i = -1; i < s.length(); i++) {
            if (i != -1) writer.append(s.charAt(i));
            if (idxToStr.containsKey(i + 1)) {
                String str = idxToStr.get(i + 1);
                for (int j = 0; j < str.length(); j++) {
                    writer.append(str.charAt(j));
                }
            }
        }

        return writer.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            int n = Integer.parseInt(reader.readLine());
            Map<Integer, String> idxToStr = new HashMap<>();
            for (int i = 0; i < n; i++) {
                StringTokenizer tokens = new StringTokenizer(reader.readLine());
                String str = tokens.nextToken();
                int position = Integer.parseInt(tokens.nextToken());
                idxToStr.put(position, str);
            }

            String result = getInsertedStr(s, idxToStr);
            System.out.println(result);
        }
    }
}
