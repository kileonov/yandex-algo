package sortings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InsertionSort {

    private static List<Integer> insertionSort(List<Integer> numbers) {
        int temp;
        for (int i = 0; i < numbers.size(); i++) {
            temp = numbers.get(i);
            var j = i;
            while (j > 0 && temp < numbers.get(j - 1)) {
                numbers.set(j, numbers.get(j - 1));
                j--;
            }
//            System.out.println("before Step " + i + " list " + numbers);
            numbers.set(j, temp);
            System.out.println("Step " + i + " list " + numbers);
        }
        // 11 2 9 7 1
        return numbers;
    }

    public static void main(String[] args) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> numbers = Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            numbers = insertionSort(numbers);
            System.out.println(numbers);
        }
    }
}
