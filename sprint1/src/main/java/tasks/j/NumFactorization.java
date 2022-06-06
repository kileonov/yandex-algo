package tasks.j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class NumFactorization {

    private static List<Integer> factorize(int n) {
        List<Integer> result = new ArrayList<>();
        int currentNum = n;

        for (int i = 2; i <= currentNum / i; i++) {
            while (currentNum % i == 0) {
                result.add(i);
                currentNum /= i;
            }
        }

        if (currentNum > 1) result.add(currentNum);

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            List<Integer> factorization = factorize(n);
            for (int elem : factorization) {
                writer.write(elem + " ");
            }
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
