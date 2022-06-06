package polynomhash;

import java.io.IOException;

public class PolynomHashBreaker {

    private static long calculatePolynomHash(String str, int a, int m) {
        long hash = 0L;
        for (char ch : str.toCharArray()) {
            hash = (hash * a + ch) % m;
        }
        return hash;
    }

    private static String doRec(String curStr, int left, Long target, int a, int m) {
        if (left <= 0) return "";

        for (int i = 'a'; i <= 'z'; i++) {
            char letter = (char) i;
            doRec(curStr + letter, left - 1, target, a, m);
            System.out.println(curStr);
            if (calculatePolynomHash(curStr, a, m) == target) return curStr;
        }

        return "";
    }

    public static void main(String[] args) throws IOException {
        int a = 1000;
        int m = 123_987_123;
        String str1 = "zwn";
        var str1Result = calculatePolynomHash(str1, a, m);

        String str2Result = doRec("", 4, str1Result, a, m);
        System.out.println("result = " + str2Result);

//        System.out.println(calculatePolynomHash(str1, a, m));
//        System.out.println(calculatePolynomHash(str2, a, m));
    }
}
