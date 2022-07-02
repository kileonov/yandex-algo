package i;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SearchTreeNCombinations {

    private static class Data {
        int value;
        int predecessor;
        char direction;

        public Data(int value, int predecessor, char direction) {
            this.value = value;
            this.predecessor = predecessor;
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "value=" + value +
                    ", predecessor=" + predecessor +
                    ", direction=" + direction +
                    '}';
        }
    }

    private static void calculateNSearchTrees(List<Integer> possibleNodes, List<Data> result) {
        if (possibleNodes.isEmpty()) {
            System.out.println("result = " + result);
            return;
        }

        for (int i = 0; i < possibleNodes.size(); i++) {
            Integer child = possibleNodes.get(i);

            List<Integer> other = new ArrayList<>(possibleNodes);
            other.remove(i);

            if (result.isEmpty()) {
                List<Data> nextResult = new ArrayList<>();
                nextResult.add(new Data(child, -1, 't'));
                calculateNSearchTrees(other, nextResult);
            } else {
                int predecessor = result.get(result.size() - 1).value;
                List<Data> nextResult = new ArrayList<>(result);
                if (predecessor > child) {
                    nextResult.add(new Data(child, predecessor, 'l'));
                } else {
                    nextResult.add(new Data(child, predecessor, 'r'));
                }
                calculateNSearchTrees(other, nextResult);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> possibleValues = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                possibleValues.add(i);
            }
            calculateNSearchTrees(possibleValues,new ArrayList<>());
//            System.out.println(result);
        }
    }
}
