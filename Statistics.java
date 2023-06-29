/**
 * Vince Verdugo
 * CS 160L
 */
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
public class Statistics {
    private static List<CoffeeOrder> orders = new ArrayList<CoffeeOrder>();

    public Statistics(List<CoffeeOrder> orders) {}

    public static double getTotal(LocalDateTime start, LocalDateTime end) {
        return 0.0;
    }
    public static double getAvgCost() {
        return 0.0;
    }
    public static String[] getPopularOrders(int maxRank) {
        return null;
    }
    public static String printStatistics() {
        return "";
    }
}
