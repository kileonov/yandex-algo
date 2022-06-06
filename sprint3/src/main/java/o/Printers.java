package o;

import java.io.IOException;

public class Printers {

    private static boolean check(int t, int x, int y, int n) {
//        int printedPapers = (x + y) * t;

        double dy = (double) y;
        double dx = (double) x;
        double v = t * (1.0 / dx + 1.0 / dy);
        int intR = (int) (v * 10.0) / 10;
        return intR >= n;
    }

    private static int calculateTime(int x, int y, int n) {
        int left = 0;
        int right = n * x + 1;

        while (right - left > 1) {
            int mid = (right + left) / 2;
            if (check(mid, x, y, n)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) throws IOException {
        int x = 5;
        int y = 10;
        int n = 3;

        var result = calculateTime(x, y, n);
        System.out.println(result);
    }
}
