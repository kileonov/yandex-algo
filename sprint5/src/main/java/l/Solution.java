package l;

public class Solution {
    public static int siftDown(int[] heap, int idx) {
        int lChildIdx = idx * 2;
        int rChildIdx = idx * 2 + 1;

        if (lChildIdx > heap.length) return idx;

        int maxChildIdx;
        if (rChildIdx < heap.length && heap[rChildIdx] > heap[lChildIdx]) {
            maxChildIdx = rChildIdx;
        } else {
            maxChildIdx = lChildIdx;
        }

        if (heap[idx] < heap[maxChildIdx]) {
            swap(heap, idx, maxChildIdx);
            return siftDown(heap, maxChildIdx);
        }
        return idx;
    }

    private static void swap(int[] heap, int i, int k) {
        int temp = heap[i];
        heap[i] = heap[k];
        heap[k] = temp;
    }

    private static void test() {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        int result = siftDown(sample, 2);
        System.out.println("result = " + result);
        assert result == 5;
    }

    public static void main(String[] args) {
        test();
    }
}
