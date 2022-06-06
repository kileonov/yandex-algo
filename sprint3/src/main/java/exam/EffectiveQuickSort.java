package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// Passed ID 68551812
/**
 * -- ПРИНЦИП РАБОТЫ --
 * Для сортировки массива используется быстрая сортировка на месте. Принцип практически такой же как и у быстрой
 * сортировки с доп выделением массивов. Берем рандомный элемент в массиве - partition, и размещаем все элементы, которые
 * меньше его слева, а больше справа. Для этого используется метод 2 указателей и swap. Возвращаем новый указатель
 * на partition и снова сортируем левую и правую части рекурсивно.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * При выборке рандомного элемента из последовательности, сортировка происходит таким образом, что
 * слева от этого элемента остаются меньшие, а справа большие. Потом сортируем левую и правую части по такому
 * же принципу. Значит в конце нам останется, только склеить отсортированные половинки.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * В худшем случае: Time: O(n^2); Space: O(N).
 * В среднем случае работает намного быстрее, так как выбирается рандомный элемент (partition) из массива:
 * Time: θ(nlog(n)); Space: O(log(n)).
 */
public class EffectiveQuickSort {

    private static void sortByEffectiveQuickSort(List<Participant> participants, int left, int right) {
        if (right <= left) return;

        final int pivotIdx = pickPivot(left, right);
        final int delimiter = partition(participants, pivotIdx, left, right);
        sortByEffectiveQuickSort(participants, left, delimiter);
        sortByEffectiveQuickSort(participants, delimiter + 1, right);
    }

    private static int partition(List<Participant> participants, int pivotIdx, int left, int right) {
        final Participant pivot = participants.get(pivotIdx);
        while (right > left) {
            while (participants.get(left).compareTo(pivot) < 0) {
                left++;
            }
            while (participants.get(right).compareTo(pivot) > 0) {
                right--;
            }
            swap(participants, left, right);
        }
        return left;
    }

    private static int pickPivot(int left, int right) {
        return ThreadLocalRandom.current().nextInt(left, right);
    }

    private static void swap(List<Participant> participants, int left, int right) {
        final Participant temp = participants.get(left);
        participants.set(left, participants.get(right));
        participants.set(right, temp);
    }

    private static String generateOutput(List<Participant> participants) {
        // cannot calculate outputSize in streams because of ML
        var outputSize = 0;
        for (Participant participant : participants) {
            outputSize += participant.name.length() + System.lineSeparator().length();
        }
        final StringBuilder builder = new StringBuilder(outputSize);
        participants.forEach(participant -> builder.append(participant.name).append(System.lineSeparator()));
        return builder.toString();
    }

    private static class Participant implements Comparable<Participant> {
        private final String name;
        private final int solvedTasks;
        private final int fine;

        public String getName() {
            return name;
        }

        public int getSolvedTasks() {
            return solvedTasks;
        }

        public int getFine() {
            return fine;
        }

        public Participant(String name, int solvedTasks, int fine) {
            this.name = name;
            this.solvedTasks = solvedTasks;
            this.fine = fine;
        }

        static Participant of(String participantInfo) {
            final String[] info = participantInfo.split(" ");
            return new Participant(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]));
        }

        @Override
        public int compareTo(Participant o) {
            return Comparator.comparing(Participant::getSolvedTasks, Comparator.reverseOrder())
                    .thenComparing(Participant::getFine)
                    .thenComparing(Participant::getName)
                    .compare(this, o);
        }
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int n = Integer.parseInt(reader.readLine());

            final List<Participant> participants = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                participants.add(Participant.of(reader.readLine()));
            }
            sortByEffectiveQuickSort(participants, 0, participants.size() - 1);

            System.out.println(generateOutput(participants));
        }
    }
}
