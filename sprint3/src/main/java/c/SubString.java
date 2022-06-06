package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SubString {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            String t = reader.readLine();
            List<Character> list = new ArrayList<>(t.length());
            for (int i = 0; i < t.length(); i++) {
                list.add(t.charAt(i));
            }
            var result = isSubstring(s, list, 0);
            System.out.println(result == s.length() ? "True" : "False");
        }
    }

    private static int isSubstring(String s, List<Character> t, int i) {
        if (t.size() == 1) {
            return i < s.length() && s.charAt(i) == t.get(0) ? i + 1 : i;
        }

        int leftComparison = isSubstring(s, t.subList(0, t.size() / 2), i);
        int rightComparison = isSubstring(s, t.subList(t.size() / 2, t.size()), leftComparison);


        return Math.max(leftComparison, rightComparison);
    }
}
