package j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BubbleSort {

    private static final String DELIMITER = " ";

    private static List<Integer> bubbleSort(List<Integer> numbers) {
        boolean hasChanged = false;
        boolean isSame = true;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size() - i - 1; j++) {
                if (numbers.get(j) > numbers.get(j + 1)) {
                    int temp = numbers.get(j);
                    numbers.set(j, numbers.get(j + 1));
                    numbers.set(j + 1, temp);
                    hasChanged = true;
                    isSame = false;
                }
            }
            if (hasChanged) {
                StringBuilder elements = new StringBuilder(numbers.size());
                for (Integer number : numbers) {
                    elements.append(number).append(DELIMITER);
                }
                System.out.println(elements);
                hasChanged = false;
            };
        }

        if (isSame) {
            StringBuilder elements = new StringBuilder(numbers.size());
            for (Integer number : numbers) {
                elements.append(number).append(DELIMITER);
            }
            System.out.println(elements);
        }

        return numbers;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());

            String line = reader.readLine();
            int currentNum = 0;
            boolean isNegative = false;
            List<Integer> numbers = new ArrayList<>(n);

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == '-') {
                    isNegative = true;
                } else if (c != ' ') {
                    currentNum = 10 * currentNum + Character.getNumericValue(c);
                } else {
                    numbers.add(isNegative ? -currentNum : currentNum);
                    currentNum = 0;
                    isNegative = false;
                }
            }
            numbers.add(isNegative ? -currentNum : currentNum);
            bubbleSort(numbers);
        }
    }
}
