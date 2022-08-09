package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BorderControl {

    private static boolean isEqual(String str1, String str2) {
        if (str2.length() > str1.length()) {
            String temp = str1;
            str1 = str2;
            str2 = temp;
        }

        int idxStr1 = 0;
        int idxStr2 = 0;
        int counter = 0;
        for (int i = 0; i < str2.length(); i++) {
            if (str1.charAt(idxStr1) != str2.charAt(idxStr2)) {
                if (str1.length() != str2.length()) {
                    idxStr1++;
                }
                counter++;
                if (counter > 1) break;
            }
            idxStr1++;
            idxStr2++;
        }

        return counter <= 1;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str1 = reader.readLine();
            String str2 = reader.readLine();
            boolean result = isEqual(str1, str2);
            System.out.println(result ? "OK" : "FAIL");
        }
    }
}
