package tasks.k;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListForm {

    private static List<Integer> getSum(List<Integer> numberList, int k) {
        var result = new ArrayList<Integer>();
        var idx = 0; var secondNum = k; var previousReminder = 0;

        while (idx < numberList.size() && secondNum != 0) {
            int firstDigit = numberList.get(numberList.size() - 1 - idx);
            int secondDigit = secondNum % 10;

            var sum = firstDigit + secondDigit + previousReminder;

            result.add(sum % 10);
            previousReminder = sum / 10;

            secondNum /= 10;
            idx++;
        }

        for (int i = idx; i < numberList.size(); i++) {
            result.add(numberList.get(numberList.size() - 1 - i) + previousReminder);
            previousReminder = 0;
        }

        while (secondNum != 0) {
            int secondDigit = secondNum % 10;
            result.add(secondDigit + previousReminder);
            secondNum /= 10;
            previousReminder = 0;
        }

        if (previousReminder == 1) result.add(previousReminder);

        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int numberLength = readInt(reader);
            List<Integer> numberList = readList(reader);
            int k = readInt(reader);
            List<Integer> sum = getSum(numberList, k);
            for (int elem : sum) {
                writer.write(elem + " ");
            }
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
