import db.DatabaseManager;
import file.ChooserFile;
import file.FileUtil;
import statistic.CalculatingStatistics;
import statistic.Statistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        final DatabaseManager db = DatabaseManager.getInstance();

        System.out.println("***** Choose text file *****");
        //String filePath = "D:/wiki_statistics.txt";                     // first option
        String filePath = new ChooserFile().getFile().getAbsolutePath();  // second option

        List<String> listRows = FileUtil.readFile(filePath);

        System.out.println("***** Write file into database *****");
        db.writeFile(filePath);

        System.out.println("***** Getting statistics for rows and write to the database *****");
        Map<Integer, Statistics> cacheStatistics = new HashMap<>();
        listRows.forEach(row -> db.writeStatistic4Row(CalculatingStatistics.getStatistic4Row(row), cacheStatistics));

        System.out.println("***** Getting aggregate statistics for rows and write to the database *****");
        db.writeAggregateStatistic4Rows(CalculatingStatistics.getAggregateStatistic4Rows(cacheStatistics));

        db.closeConnection();
    }

}

