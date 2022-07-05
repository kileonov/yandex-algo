package m;

public class Solution {
    public static int siftUp(int[] heap, int idx) {
        if (idx <= 1) return 1;

        int parentIdx = idx / 2;
        if (heap[idx] > heap[parentIdx]) {
            swap(heap, idx, parentIdx);
            return siftUp(heap, parentIdx);
        }
        return idx;
    }

    private static void swap(int[] heap, int i, int k) {
        int temp = heap[i];
        heap[i] = heap[k];
        heap[k] = temp;
    }

    private static void test() {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        int result = siftUp(sample, 5);
        System.out.println("result = " + result);
        assert result == 1;
    }

    public static void main(String[] args) {
        test();
    }
}
