package tasks.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;
import java.io.IOException;

public class ChaoticWeather {

    private static int getWeatherRandomness(List<Integer> temperatures) {
        // [1, 2, 5, 4, 8]
        var numOfChaoticDays = 0;
        for (int i = 0; i < temperatures.size(); i++) {
            Integer currentTemp = temperatures.get(i);

            var isChaoticYesterday = i - 1 < 0 || currentTemp > temperatures.get(i - 1);
            var isChaoticTomorrow = i + 1 == temperatures.size() || currentTemp > temperatures.get(i + 1);

            if (isChaoticYesterday && isChaoticTomorrow) numOfChaoticDays++;
        }
        return numOfChaoticDays;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int numberOfDays = readInt(reader);
            List<Integer> temperatures = readList(reader);
            int chaosNumber = getWeatherRandomness(temperatures);
            System.out.println(chaosNumber);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }
}
