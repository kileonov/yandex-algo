package tasks.f;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Palindrome {

    private static boolean isLetterOrNum(char ch) {
        var ascii = (int) ch;
        return  ascii >= 65 && ascii <= 90 ||
                ascii >= 97 && ascii <= 122 ||
                ascii >= 48 && ascii <= 57;
    }

    private static boolean isPalindrome(String text) {
        if (text.isEmpty()) return true;
        int left = 0; int right = text.length() - 1;

        while (left < right) {
            char leftC = text.charAt(left);
            if (!isLetterOrNum(leftC)) {
                left++;
                continue;
            }
            char rightC = text.charAt(right);
            if (!isLetterOrNum(rightC)) {
                right--;
                continue;
            }

            if (Character.toLowerCase(leftC) != Character.toLowerCase(rightC)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = reader.readLine();
            if (isPalindrome(text)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }
}
