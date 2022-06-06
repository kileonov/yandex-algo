package exam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuickSort {

    public static List<List<Integer>> partition(List<Integer> array, int pivot) {
        List<Integer> left = new ArrayList<Integer>();
        List<Integer> center = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();

        Integer pivotElement = array.get(pivot);
        for (Integer element : array) {
            if (element < pivotElement) left.add(element);
            else if (element > pivotElement) right.add(element);
            else center.add(element);
        }

        return Stream.of(left, center, right).collect(Collectors.toList());
    }

    public static List<Integer> quicksort(List<Integer> array) {
        if (array.size() < 2) return array;

        Random random = new Random();
        int pivot = random.nextInt(array.size());
        List<List<Integer>> sortedParts = partition(array, pivot);
        List<Integer> left = quicksort(sortedParts.get(0));
        List<Integer> center = sortedParts.get(1);
        List<Integer> right = quicksort(sortedParts.get(2));
        return Stream.of(left, center, right).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(quicksort(List.of(7, 5, 4, 5, 3, 8, 5)));
    }
}
