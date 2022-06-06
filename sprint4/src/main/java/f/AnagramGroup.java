package f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AnagramGroup {

    private static void calculateAnagramGroups(String[] words) {
        List<String> groups = new ArrayList<>();
        Map<String, List<Integer>> anagramToIndices = new HashMap<>();

        var groupIdx = 0;
        for (int i = 0; i < words.length; i++) {
            char[] arr = words[i].toCharArray();
            Arrays.sort(arr);
            var sortedStr = Arrays.toString(arr);
            if (!anagramToIndices.containsKey(sortedStr)) {
                anagramToIndices.put(sortedStr, new ArrayList<>());
                groups.add(groupIdx++, sortedStr);
            }
            anagramToIndices.get(sortedStr).add(i);
        }

        StringBuilder result = new StringBuilder();
        for (String group : groups) {
            for (Integer idx : anagramToIndices.get(group)) {
                result.append(idx).append(" ");
            }
            result.append("\n");
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            String[] words = reader.readLine().split(" ");
            calculateAnagramGroups(words);
        }
    }
}
