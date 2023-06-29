/**
 * Vince Verdugo
 * CS160L
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CoffeeOrder {
    private List<Coffee> coffees;
    private LocalDateTime orderDate;
    private boolean discount = false;
    private double percentage = 1.0;
    private String userDiscount;
    private String userName;

    /**
     * Constructor
     */
    public CoffeeOrder() {
        coffees = new ArrayList<>();
        orderDate = LocalDateTime.now();
    }

    /**
     * Adds coffee to order
     * @param c instance of Coffee
     */
    public void addCoffee(Coffee c) {
        coffees.add(c);
    }

    /**
     * Collects total cost for each coffee in order
     * @return double
     */
    public double getTotal() {
        double total = 0;
        for (Coffee coffee : coffees) {
            total += coffee.getCost();
        }
        if (discount) {
            return total * percentage;
        }
        return total;
    }
    /**
     * Sets the percentage of discount
     * @param selection
     */
    public void setDiscount(int selection) {
        switch (selection) {
            case 1:
                percentage = .85;
                userDiscount = "Police/Firefighter";
            case 2:
                percentage = .85;
                userDiscount = "Military";
            case 3:
                percentage = .90;
                userDiscount = "Student";
            case 4:
                percentage = .50;
                userDiscount = "Employee";
        }
    }

    public void toDiscount() {
        discount = true;
    }
    public void setName(String name) {
        userName = name;
    }

    /**
     * Builds a string using StringBuilder to format a receipt
     * @return order.toString()
     */
    public String printOrder() {
        StringBuilder order = new StringBuilder("ORDER RECEIPT\n");
        order.append(String.format("Timestamp: %s%n", orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma"))));
        order.append(userName);
        order.append("\n");
        for (int i = 0; i < coffees.size(); i++) {
            Coffee coffee = coffees.get(i);
            order.append(String.format("Item %d: %s - %.2f%n", i + 1, coffee.printCoffee(), coffee.getCost()));
        }
        if (discount) {
            order.append(userDiscount);
            order.append(" discount applied\n");

        }
        order.append(String.format("TOTAL = %.2f%n", getTotal()));
        return order.toString();
    }
}
