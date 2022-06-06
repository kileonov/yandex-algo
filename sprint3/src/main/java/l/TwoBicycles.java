package l;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TwoBicycles {

    private static void findDayToBuyRec(int[] money, int price, int leftIdx, int rightIdx) {
        if (rightIdx <= leftIdx) {
            int idx = money[leftIdx] < price ? -1 : leftIdx + 1;
            System.out.print(idx + " ");
            return;
        }

        int mid = (leftIdx + rightIdx) / 2;
        int currentAmount = money[mid];
        if (currentAmount >= price) findDayToBuyRec(money, price, leftIdx, mid);
        else findDayToBuyRec(money, price, mid + 1, rightIdx);
    }

    private static void findDayToBuy(int[] money, int price) {
        int leftIdx = 0;
        int rightIdx = money.length - 1;

        while (rightIdx > leftIdx) {
            int mid = (leftIdx + rightIdx) / 2;
            int currentAmount = money[mid];
            if (currentAmount >= price) rightIdx = mid;
            else leftIdx = mid + 1;
        }

        int idx = money[leftIdx] < price ? -1 : leftIdx + 1;
        System.out.print(idx + " ");
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());

            int[] money = new int[n];
            String line = reader.readLine();
            int currentNum = 0;
            int counter = 0;
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c != ' ') {
                    currentNum = 10 * currentNum + Character.getNumericValue(c);
                }
                else {
                    money[counter++] = currentNum;
                    currentNum = 0;
                }
            }
            money[counter] = currentNum;
            int s = Integer.parseInt(reader.readLine());

//            findDayToBuy(money, s);
//            findDayToBuy(money, 2 * s);
            findDayToBuyRec(money, s, 0, money.length - 1);
            findDayToBuyRec(money, 2 * s, 0, money.length - 1);
        }
    }
}
