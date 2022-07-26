package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Timetable {

    static class TimeTable implements Comparable<TimeTable> {
        double start;
        double end;

        public double getStart() {
            return start;
        }

        public double getEnd() {
            return end;
        }

        public TimeTable(double start, double end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(TimeTable o) {
            return Comparator.comparingDouble(TimeTable::getEnd)
                    .thenComparing(TimeTable::getStart)
                    .compare(this, o);
        }

        @Override
        public String toString() {
            return "TimeTable{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    private static void getMostLectures(List<TimeTable> timeTables) {
        Collections.sort(timeTables);
        List<TimeTable> result = new ArrayList<>();
        TimeTable previous = new TimeTable(-Double.MIN_VALUE, -Double.MIN_VALUE);

        for (TimeTable timeTable : timeTables) {
            if (timeTable.start >= previous.end) {
                previous = timeTable;
                result.add(previous);
            }
        }

        StringBuilder writer = new StringBuilder();
        writer.append(result.size()).append(System.lineSeparator());
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat myDF = new DecimalFormat("0.##", otherSymbols);
        for (TimeTable timeTable : result) {
            writer.append(myDF.format(timeTable.start)).append(" ").append(myDF.format(timeTable.end)).append(System.lineSeparator());
        }
        System.out.println(writer);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            List<TimeTable> startToEndTimeTables = readTimeTables(reader, n);
            getMostLectures(startToEndTimeTables);
        }
    }

    public static List<TimeTable> readTimeTables(BufferedReader reader, int n) throws IOException {
        if (n == 0) return Collections.emptyList();
        List<TimeTable> elements = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            StringTokenizer times = new StringTokenizer(reader.readLine());

            elements.add(new TimeTable(Double.parseDouble(times.nextToken()), Double.parseDouble(times.nextToken())));
        }

        return elements;
    }
}
