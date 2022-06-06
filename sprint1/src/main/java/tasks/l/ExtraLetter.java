package tasks.l;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ExtraLetter {

    private static char getExcessiveLetter(String s, String t) {
        var letterToNum = new HashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);
            if (!letterToNum.containsKey(ch)) letterToNum.put(ch, 0);
            letterToNum.put(ch, letterToNum.get(ch) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            var ch = t.charAt(i);
            if (!letterToNum.containsKey(ch)) return ch;
            letterToNum.put(ch, letterToNum.get(ch) - 1);
        }

        for (Map.Entry<Character, Integer> chToNum : letterToNum.entrySet()) {
            if (chToNum.getValue() != 0) return chToNum.getKey();
        }

        throw new RuntimeException("No character is found!");
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            String t = reader.readLine();
            System.out.println(getExcessiveLetter(s, t));

        }
    }
}
