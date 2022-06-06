package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Clubs {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());

            List<String> clubs = new ArrayList<>(n);
            int currentIdx = 0;
            Map<String, Integer> clubToIdx = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String club = reader.readLine();
                if (!clubToIdx.containsKey(club)) {
                    clubToIdx.put(club, currentIdx);
                    clubs.add(currentIdx, club);
                    currentIdx++;
                }
            }

            StringBuilder builder = new StringBuilder();
            clubs.forEach(club -> builder.append(club).append(System.lineSeparator()));
            System.out.println(builder);
        }
    }
}
