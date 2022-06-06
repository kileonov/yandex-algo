package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// Passed ID 68805023
/**
 * -- ПРИНЦИП РАБОТЫ --
 * Для быстрого нахождения релевантных документов нужно уметь хранить 3 модели данных: Слово -> Документ -> Количество.
 * В начале собираем хэш таблицы. Для этого проходимся по каждому документу и для каждого слова считаем
 * его вхождение в данный документ. В итоге получаем структуру описанную выше.
 * Потом для каждого поиска для каждого уникального слова ищем релевантные документы.
 * В конце релевантные документы сортируем по кол-ву вхождений и индексу и берем первые 5 элементов.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Для каждого документы считается кол-во вхождение слова. Все это объединяется в верхнюю иерархию по слову,
 * а значит каждый ключ (слово) имеет (документ + кол-во вхождний этого слова в документ). А значит можем
 * искать по слову и документам.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Time:
 * O(n * N_S) - считаем кол-во вхождений в каждый документ
 * O(m * M_S) - отбираем уникальные слова поиска
 * O(m * n * N_S * M_S) - считаем вхождения слов для каждого поиска в каждый документ
 * O(m * logm) - сортируем результат по релевантности
 * Итого Time = O(n * N_S + m * M_S + m * n * N_S * M_S + m * logm) = O(mn + mlogm)
 * Space считается точно так же, Space = O(mn)
 */
public class SearchSystem {

    private static final int MAX_SEARCH_RESULT = 5;
    private static final int EFFECTIVE_WORD_SIZE = 2048;
    private static final int EFFECTIVE_SEARCH_SIZE = 1024;
    private static final int EFFECTIVE_DOC_TO_WORD_SIZE = 256;

    private static List<Integer> calculateSearchRelevance(Map<String, Map<Integer, Integer>> wordToDocsCount, Set<String> search) {

        final Map<Integer, DocHelper> searchResult = new HashMap<>();
        for (final String word : search) {
            if (!wordToDocsCount.containsKey(word)) {
                continue;
            }

            final Map<Integer, Integer> docToCount = wordToDocsCount.get(word);
            for (final Map.Entry<Integer, Integer> entry : docToCount.entrySet()) {
                if (!searchResult.containsKey(entry.getKey())) {
                    searchResult.put(entry.getKey(), new DocHelper(entry.getKey(), 0));
                }
                searchResult.get(entry.getKey()).addCounter(entry.getValue());
            }
        }


        final List<DocHelper> relevantSearchResult = new ArrayList<>(searchResult.values());
        return relevantSearchResult.stream()
                .sorted()
                .limit(MAX_SEARCH_RESULT)
                .map(doc -> doc.idx + 1)
                .collect(Collectors.toList());
    }

    private static Set<String> getInputSearch(final BufferedReader reader) throws IOException {
        final Set<String> uniqueWords = new HashSet<>(EFFECTIVE_SEARCH_SIZE);

        final String line = reader.readLine();
        final StringBuilder word = new StringBuilder();
        for (char ch : line.toCharArray()) {
            if (ch != ' ') {
                word.append(ch);
                continue;
            }

            uniqueWords.add(word.toString());
            word.setLength(0);
        }
        uniqueWords.add(word.toString());
        return uniqueWords;
    }

    private static Map<String, Map<Integer, Integer>> getInputDocuments(final BufferedReader reader) throws IOException {
        final int n = Integer.parseInt(reader.readLine());

        final Map<String, Map<Integer, Integer>> wordToDocs = new HashMap<>(EFFECTIVE_WORD_SIZE);
        for (int i = 0; i < n; i++) {

            final String line = reader.readLine();
            final StringBuilder word = new StringBuilder();

            for (char ch : line.toCharArray()) {
                if (ch != ' ') {
                    word.append(ch);
                    continue;
                }

                assignWordToDoc(wordToDocs, i, word.toString());
                word.setLength(0);
            }
            assignWordToDoc(wordToDocs, i, word.toString());
        }

        return wordToDocs;
    }

    private static void assignWordToDoc(Map<String, Map<Integer, Integer>> wordToDocs, int i, String word) {
        if (!wordToDocs.containsKey(word)) {
            wordToDocs.put(word, new HashMap<>(EFFECTIVE_DOC_TO_WORD_SIZE));
        }
        final Map<Integer, Integer> integerIntegerMap = wordToDocs.get(word);
        integerIntegerMap.put(i, integerIntegerMap.getOrDefault(i, 0) + 1);
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final Map<String, Map<Integer, Integer>> docs = getInputDocuments(reader);

            final StringBuilder builder = new StringBuilder();

            final int m = Integer.parseInt(reader.readLine());
            for (int i = 0; i < m; i++) {
                final Set<String> search = getInputSearch(reader);

                final List<Integer> result = calculateSearchRelevance(docs, search);
                for (Integer column : result) {
                    builder.append(column).append(" ");
                }
                builder.append(System.lineSeparator());
            }
            System.out.println(builder);
        }
    }

    private static class DocHelper implements Comparable<DocHelper> {
        private final int idx;
        private int counter;

        public DocHelper(int idx, int counter) {
            this.idx = idx;
            this.counter = counter;
        }

        public void addCounter(int num) {
            counter += num;
        }

        public int getIdx() {
            return idx;
        }

        public int getCounter() {
            return counter;
        }

        @Override
        public int compareTo(DocHelper o) {
            return Comparator.comparing(DocHelper::getCounter, Comparator.reverseOrder())
                    .thenComparing(DocHelper::getIdx)
                    .compare(this, o);
        }
    }
}
