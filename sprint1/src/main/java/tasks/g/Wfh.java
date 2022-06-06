package tasks.g;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Wfh {

    private static String getBinaryNumber(int n) {
        var result = new StringBuilder();
        var currentNum = n;
        while (currentNum != 0) {
            result.append(currentNum % 2);
            currentNum /= 2;
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            System.out.println(getBinaryNumber(n));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
