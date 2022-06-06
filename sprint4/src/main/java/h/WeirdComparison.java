package h;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class WeirdComparison {

    private static boolean hasSameContent(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> chToCode = new HashMap<>(26);

        for (int i = 0; i < s.length(); i++) {
            char chS = s.charAt(i);
            char chT = t.charAt(i);
            if (!chToCode.containsKey(chS)) {
                if (chToCode.values().contains(chT)) return false;
                chToCode.put(chS, chT);
            } else {
                if (chToCode.get(chS) != chT) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            String t = reader.readLine();

            System.out.println(hasSameContent(s, t) ? "YES" : "NO");
        }
    }
}
