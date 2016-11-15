package db;

import statistic.Statistics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.io.File;
import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost/statistics_db?useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection conn;
    private static DatabaseManager databaseManager;

    private DatabaseManager(){
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error getting connection " + e);
        }
    }

    public static DatabaseManager getInstance(){
        if(databaseManager == null){
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error closing connection " + e);
        }
    }

    public void writeAggregateStatistic4Rows(Statistics statistic){
        try (PreparedStatement ps = createPreparedStatement4AggregateStatistic4Rows(conn, statistic)){
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error writeAggregateStatistic4Rows " + e);
        }
    }

    public void writeStatistic4Row(Statistics statistic, Map<Integer, Statistics> cacheStatistics) {
        try (PreparedStatement ps = createPreparedStatement4Statistic4Row(conn, statistic)){
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    statistic.setId(generatedKeys.getInt(1));
                    cacheStatistics.put(statistic.getId(), statistic);
                }
            }
        } catch (Exception e) {
            System.err.println("Error writeStatistic4Row " + e);
        }
    }

    public void writeFile(String filePath){
        try (PreparedStatement ps = createPreparedStatement4SaveFile(conn, filePath)){
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error writeFile " + e);
        }
    }

    public PreparedStatement createPreparedStatement4SaveFile(Connection con, String filePath) throws SQLException, FileNotFoundException {
        String sql = "INSERT INTO file (file_name, file) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, filePath);
        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        ps.setCharacterStream(2, reader, (int) file.length());
        return ps;
    }

    public PreparedStatement createPreparedStatement4Statistic4Row(Connection con, Statistics statistic) throws SQLException {
        String sql = "INSERT INTO statistics_by_file (line, longest_word, shortest_word, line_length, average_word_length) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, statistic.getLine());
        ps.setInt(2, statistic.getLongestWord());
        ps.setInt(3, statistic.getShortestWord());
        ps.setInt(4, statistic.getLineLength());
        ps.setDouble(5, statistic.getAverageWordLength());
        return ps;
    }

    public PreparedStatement createPreparedStatement4AggregateStatistic4Rows(Connection con, Statistics statistic) throws SQLException {
        String sql = "INSERT INTO aggregate_statistic (id, aggregate_longest_word, aggregate_shortest_word, aggregate_line_length, aggregate_average_word_length) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, statistic.getId());
        ps.setInt(2, statistic.getLongestWord());
        ps.setInt(3, statistic.getShortestWord());
        ps.setInt(4, statistic.getLineLength());
        ps.setDouble(5, statistic.getAverageWordLength());
        return ps;
    }

}
