package k;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CompareTwoStrings {

    private static int compareStrings(String str1, String str2) {
        int idxStr1 = 0;
        int idxStr2 = 0;

        while (idxStr1 < str1.length() || idxStr2 < str2.length()) {

            while (idxStr1 < str1.length() && ((int) str1.charAt(idxStr1)) % 2 != 0) {
                idxStr1++;
            }
            char ch1 = idxStr1 < str1.length() ? str1.charAt(idxStr1) : ' ';

            while (idxStr2 < str2.length() && ((int) str2.charAt(idxStr2)) % 2 != 0) {
                idxStr2++;
            }
            char ch2 = idxStr2 < str2.length() ? str2.charAt(idxStr2) : ' ';

            if (ch1 > ch2) return 1;
            else if (ch1 < ch2) return -1;

            idxStr1++;
            idxStr2++;
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str1 = reader.readLine();
            String str2 = reader.readLine();
            int result = compareStrings(str1, str2);
            System.out.println(result);
        }
    }
}
