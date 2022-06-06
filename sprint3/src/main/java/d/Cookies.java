package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Cookies {

    private static int getResultRec(List<Integer> wantedCookies, Map<Integer, Integer> sizeToCounter, int result) {
        if (wantedCookies.size() == 1) {
            Integer wantedCookie = wantedCookies.get(0);
            Integer counter = sizeToCounter.getOrDefault(wantedCookie, 0);
            if (counter > 0) {
                sizeToCounter.put(wantedCookie, counter - 1);
                return result + 1;
            }
            return result;
        }

        int leftResult = getResultRec(wantedCookies.subList(0, wantedCookies.size() / 2), sizeToCounter, result);
        int rightResult = getResultRec(wantedCookies.subList(wantedCookies.size() / 2, wantedCookies.size()), sizeToCounter, leftResult);

        return Math.max(leftResult, rightResult);
    }

    private static int getMostSatisfiedNumOfChildren(List<Integer> wantedCookies, List<Integer> availableCookies) {
        Map<Integer, Integer> sizeToCounter = new HashMap<>(availableCookies.size());
        for (int i = 0; i < availableCookies.size(); i++) {
            Integer availableCookie = availableCookies.get(i);
            if (!sizeToCounter.containsKey(availableCookie)) {
                sizeToCounter.put(availableCookie, 0);
            }
            sizeToCounter.put(availableCookie, sizeToCounter.get(availableCookie) + 1);
        }

        return getResultRec(wantedCookies, sizeToCounter, 0);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> wantedCookies = readNonNegativeInts(reader, n, ' ');
            int m = Integer.parseInt(reader.readLine());
            List<Integer> availableCookies = readNonNegativeInts(reader, m, ' ');
            int result = getMostSatisfiedNumOfChildren(wantedCookies, availableCookies);
            System.out.println(result);
        }
    }

    public static List<Integer> readNonNegativeInts(BufferedReader reader, int n, char delimiter) throws IOException {
        if (n == 0) return Collections.emptyList();
        String line = reader.readLine();
        List<Integer> elements = new ArrayList<>(n);
        int currentNum = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c != delimiter) {
                currentNum = 10 * currentNum + Character.getNumericValue(c);
            }
            else {
                elements.add(currentNum);
                currentNum = 0;
            }
        }
        elements.add(currentNum);

        return elements;
    }
}
