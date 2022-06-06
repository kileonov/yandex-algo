package tasks.e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestWord {

    private static String getLongestWord(String text) {
        var longestWord = "";

        int i = 0;
        var currentWord = new StringBuilder();

        while (i < text.length()) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                if (currentWord.length() > longestWord.length()) longestWord = currentWord.toString();
                currentWord = new StringBuilder();
            } else {
                currentWord.append(ch);
            }
            i++;
        }

        if (currentWord.length() > longestWord.length()) longestWord = currentWord.toString();

        return longestWord;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int textLength = readInt(reader);
            String text = reader.readLine();
            String longestWord = getLongestWord(text);
            System.out.println(longestWord);
            System.out.println(longestWord.length());
        }

    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
