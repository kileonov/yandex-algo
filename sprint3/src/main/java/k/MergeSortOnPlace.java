package k;

import java.util.Arrays;

public class MergeSortOnPlace {
    public static int[] merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left];
        int tempIdx = 0;
        int leftIdx = left; int rightIdx = mid;

        while (leftIdx < mid && rightIdx < right) {
            if (arr[leftIdx] < arr[rightIdx]) {
                temp[tempIdx++] = arr[leftIdx++];
            } else {
                temp[tempIdx++] = arr[rightIdx++];
            }
        }

        while (leftIdx < mid) {
            temp[tempIdx++] = arr[leftIdx++];
        }

        while (rightIdx < right) {
            temp[tempIdx++] = arr[rightIdx++];
        }

        int arrIdx = left;
        for (int i = 0; i < temp.length; i++) {
            arr[arrIdx++] = temp[i];
        }

        return arr;
    }

    public static void merge_sort(int[] arr, int left, int right) {
        if (right - 1 == left) return;

        int mid = (left + right) / 2;
        merge_sort(arr, left, mid);
        merge_sort(arr, mid, right);

        merge(arr, left, mid, right);
    }

    public static void main(String[] args) {
//        int[] a = {1, 4, 9, 2, 10, 11};
//        int[] b = merge(a, 0, 3, 6);
//        System.out.println("b" + Arrays.toString(b));
//        int[] expected = {1, 2, 4, 9, 10, 11};
//        assert Arrays.equals(b, expected);
        int[] c = {1, 4, 2, 10, 1, 2};
        merge_sort(c, 0, 6);
        int[] expected2 = {1, 1, 2, 2, 4, 10};
        System.out.println("c" + Arrays.toString(c));
        assert Arrays.equals(c, expected2);
    }
}
