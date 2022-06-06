package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OldPhoneCombinations {

    private static Map<Integer, String> numberToSymbols = Map.of(
            2, "abc",
            3, "def",
            4, "ghi",
            5, "jkl",
            6, "mno",
            7, "pqrs",
            8, "tuv",
            9, "wxyz"
    );

    private static void printCombination(List<Integer> numbers, String currentSymbols, int curIdx) {
        if (curIdx == numbers.size()) {
            System.out.print(currentSymbols + " ");
            return;
        }

        Integer currentNum = numbers.get(curIdx);
        String availableSymbols = numberToSymbols.get(currentNum);
        for (int i = 0; i < availableSymbols.length(); i++) {
            var currentSymbol = availableSymbols.charAt(i);
            printCombination(numbers, currentSymbols + currentSymbol, curIdx + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            String lineNumbers = reader.readLine();
            ArrayList<Integer> digits = new ArrayList<>(lineNumbers.length());
            for (int i = 0; i < lineNumbers.length(); i++) {
                digits.add(Character.getNumericValue(lineNumbers.charAt(i)));
            }
            printCombination(digits, "", 0);
        }
    }
}
