package statistic;

public class Statistics {
    private int id;
    private String line;
    private int longestWord;
    private int shortestWord;
    private int lineLength;
    private double averageWordLength;

    public Statistics(int id, String line, int longestWord, int shortestWord, int lineLength, double averageWordLength) {
        this.id = id;
        this.line = line;
        this.longestWord = longestWord;
        this.shortestWord = shortestWord;
        this.lineLength = lineLength;
        this.averageWordLength = averageWordLength;
    }

    public Statistics() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLine() {
        return line;
    }
    public void setLine(String line) {
        this.line = line;
    }
    public int getLongestWord() {
        return longestWord;
    }
    public void setLongestWord(int longestWord) {
        this.longestWord = longestWord;
    }
    public int getShortestWord() {
        return shortestWord;
    }
    public void setShortestWord(int shortestWord) {
        this.shortestWord = shortestWord;
    }
    public int getLineLength() {
        return lineLength;
    }
    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }
    public double getAverageWordLength() {
        return averageWordLength;
    }
    public void setAverageWordLength(double averageWordLength) {
        this.averageWordLength = averageWordLength;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(averageWordLength);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + lineLength;
        result = prime * result + longestWord;
        result = prime * result + shortestWord;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Statistics other = (Statistics) obj;
        if (Double.doubleToLongBits(averageWordLength) != Double.doubleToLongBits(other.averageWordLength))
            return false;
        if (lineLength != other.lineLength)
            return false;
        if (longestWord != other.longestWord)
            return false;
        return shortestWord == other.shortestWord;
    }

    @Override
    public String toString() {
        return "statistic.Statistics [line=" + line + ", longestWord=" + longestWord + ", shortestWord=" + shortestWord
                + ", lineLength=" + lineLength + ", averageWordLength=" + averageWordLength + "]";
    }

}