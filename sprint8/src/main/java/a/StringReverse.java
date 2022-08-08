package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringReverse {

    private static char[] reverseStr(String str) {
        int lastIdx = str.length() - 1;
        int previousSpace = -1;
        
        char[] result = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                for (int j = i - 1; j > previousSpace; j--) {
                    result[lastIdx--] = str.charAt(j);
                }
                result[lastIdx--] = ' ';
                previousSpace = i;
            }
        }
        for (int i = str.length() - 1; i > previousSpace; i--) {
            result[lastIdx--] = str.charAt(i);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = reader.readLine();
            char[] result = reverseStr(str);

            StringBuilder writer = new StringBuilder();
            for (char c : result) {
                writer.append(c);
            }
            System.out.println(writer);
        }
    }
}
