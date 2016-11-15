import statistic.CalculatingStatistics;
import org.junit.Test;
import statistic.Statistics;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StatisticsTest {

	@Test
	public void test_calculateLongestWord(){
		int expected = 9;
		int actual = CalculatingStatistics.getLongestWord("Calculate statistic for each line:");
		assertEquals(expected, actual);
	}

	@Test
	public void test_calculateShortestWord(){
		int expected = 3;
		int actual = CalculatingStatistics.getShortestWord("Calculate statistic for each line:");
		assertEquals(expected, actual);
	}

	@Test
	public void test_calculateLineLength(){
		int expected = 18;
		int actual = CalculatingStatistics.getLineLength("... for each line:");
		assertEquals(expected, actual);
	}

	@Test
	public void test_calculateAverageWordLength(){
		double expected = 3;
		double actual = CalculatingStatistics.getAverageWordLength("ab abc abcd");
		assertTrue(expected == actual);
	}

	@Test
	public void test_calculateAggregateValues4AllLines(){
		Map<Integer, Statistics> map = new HashMap<>();
		map.put(1, new Statistics(1, "test", 10, 5, 20, 6));
		map.put(2, new Statistics(2, "test", 10, 5, 20, 6));
		map.put(3, new Statistics(3, "test", 10, 5, 20, 6));
		Statistics actual = CalculatingStatistics.getAggregateStatistic4Rows(map);
		Statistics expected = new Statistics(4, "test", 30, 15, 60, 18);
		assertTrue(expected.equals(actual));
	}

}
