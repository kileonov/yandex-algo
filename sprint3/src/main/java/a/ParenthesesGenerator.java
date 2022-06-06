package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ParenthesesGenerator {

    private static void printParentheses(String parentheses, int n, int opened, int closed) {
        if (opened + closed == 2 * n) {
            System.out.println(parentheses);
            return;
        }
        if (opened < n) printParentheses(parentheses + "(", n, opened + 1, closed);
        if (closed < opened) printParentheses(parentheses + ")", n, opened, closed + 1);
    }

    private static void processParentheses(int n) {
        printParentheses("", n, 0, 0);
    }

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            int parentheses = Integer.parseInt(reader.readLine());
            processParentheses(parentheses);
        }
    }
}
