package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Passed ID 69424940
/**
 * -- ПРИНЦИП РАБОТЫ --
 * Входные данные (участников) помещаем в кучу. Для этого помещаем каждый элемент в самый конец кучи и делаем
 * просеивание вверх этого элемента. В итоге получаем отсортированную max кучу. Следующим шагом достаем максимальный
 * элемент (самый верхний) добавляем его в конечный результат и делаем просеивание кучи вниз.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * На каждом шаге добавления элемента в кучу мы просеиваем вверх, следовательно, в каждый момент времени
 * мы поддерживаем характеристики кучи. При удалении элемента мы просеиваем вниз и опять же в каждый момент времени
 * поддерживаем кучу в корректном состоянии.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Time:
 *  O(nlogn) - добавление N элементов в кучу с просеиванием вверх
 *  O(nlogn) - удаление N элементов в кучу с просеиванием вниз
 *  O(1) - добавление элемента из кучи в конец результирующего массива
 *  Итог: O(nlogn) + O(nlogn) + O(1) = O(nlogn)
 * Space:
 *  O(n) - создание доп массива под кучу
 *  O(n) - создание результирующего массива
 *  Итог: O(n) + O(n) = O(n)
 */
public class HeapSort {

    private static class Heap {
        List<Participant> data;

        public Heap(List<Participant> data) {
            this.data = data;
            this.data.add(null);
        }
    }

    private static List<Participant> heapSort(List<Participant> participants) {
        final Heap heap = new Heap(new ArrayList<>(participants.size() + 1));

        for (Participant participant : participants) {
            final int index = heap.data.size();
            heap.data.add(index, participant);
            siftUp(heap.data, index);
        }

        final List<Participant> sortedArray = new ArrayList<>(participants.size());
        while (heap.data.size() > 1) {
            final Participant max = heap.data.get(1);
            sortedArray.add(max);
            heap.data.set(1, heap.data.get(heap.data.size() - 1));
            siftDown(heap.data, 1);
            heap.data.remove(heap.data.size() - 1);
        }

        return sortedArray;
    }

    public static void siftUp(List<Participant> heap, int idx) {
        if (idx <= 1) return;

        final int parentIdx = idx / 2;
        if (heap.get(idx).compareTo(heap.get(parentIdx)) > 0) {
            Collections.swap(heap, idx, parentIdx);
            siftUp(heap, parentIdx);
        }
    }

    public static void siftDown(List<Participant> heap, int idx) {
        final int lChildIdx = idx * 2;
        final int rChildIdx = idx * 2 + 1;

        if (lChildIdx >= heap.size()) return;

        final int maxChildIdx;
        if (rChildIdx < heap.size() && heap.get(rChildIdx).compareTo(heap.get(lChildIdx)) > 0) {
            maxChildIdx = rChildIdx;
        } else {
            maxChildIdx = lChildIdx;
        }

        if (heap.get(idx).compareTo(heap.get(maxChildIdx)) < 0) {
            Collections.swap(heap, idx, maxChildIdx);
            siftDown(heap, maxChildIdx);
        }
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
            return Comparator.comparing(Participant::getSolvedTasks)
                    .thenComparing(Participant::getFine, Comparator.reverseOrder())
                    .thenComparing(Participant::getName, Comparator.reverseOrder())
                    .compare(this, o);
        }
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

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int n = Integer.parseInt(reader.readLine());

            final List<Participant> participants = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                participants.add(Participant.of(reader.readLine()));
            }

            final List<Participant> sortedParticipants = heapSort(participants);
            System.out.println(generateOutput(sortedParticipants));
        }
    }
}
