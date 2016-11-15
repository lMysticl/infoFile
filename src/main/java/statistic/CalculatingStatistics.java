package statistic;

import java.util.Arrays;
import java.util.Map;

public class CalculatingStatistics {

    private CalculatingStatistics() {
        //util class
    }

    public static Statistics getStatistic4Row(String line){
        Statistics statistic = new Statistics();
        statistic.setLine(line);
        statistic.setLongestWord(getLongestWord(line));
        statistic.setShortestWord(getShortestWord(line));
        statistic.setLineLength(getLineLength(line));
        statistic.setAverageWordLength(getAverageWordLength(line));
        System.out.println(statistic);
        return statistic;
    }

    public static Statistics getAggregateStatistic4Rows(Map<Integer,Statistics> cacheStatistics) {
        Statistics aggregateStatistic = new Statistics();
        for (Statistics statistic: cacheStatistics.values()) {
            aggregateStatistic.setLongestWord(aggregateStatistic.getLongestWord() + statistic.getLongestWord());
            aggregateStatistic.setShortestWord(aggregateStatistic.getShortestWord() + statistic.getShortestWord());
            aggregateStatistic.setLineLength(aggregateStatistic.getLineLength() + statistic.getLineLength());
            aggregateStatistic.setAverageWordLength(aggregateStatistic.getAverageWordLength() + statistic.getAverageWordLength());
        }
        aggregateStatistic.setLine("...");
        System.out.println(aggregateStatistic);
        return aggregateStatistic;
    }

    public static int getLongestWord(String line) {
        String max =  Arrays.stream(splitString(line)).max((a, b) -> a.length() - b.length()).get();
        return max.length();
    }

    public static int getShortestWord(String line) {
        String min =  Arrays.stream(splitString(line)).min((a, b) -> a.length() - b.length()).get();
        return min.length();
    }

    public static int getLineLength(String line) {
        return  line.length();
    }

    public static double getAverageWordLength(String line) {
        int count = 0;
        double sum = 0;
        double average = 0;
        String[] words = splitString(line);

        for (String word : words) {
            double wordLength = word.length();
            sum += wordLength;
            count++;
        }
        if (count > 0) {
            average = sum / count;
        }
        return average;
    }

    private static String[] splitString(String line){
        return line.replaceAll("[.,:!?]", "").split("\\s+");
    }

}
