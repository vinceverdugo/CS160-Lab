/**
 * Vince Verdugo
 * CS160L
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.*;
import java.io.BufferedReader;
public class Main {
    private static Map<String, Integer> inventory = new TreeMap<>();
    private static final List<CoffeeOrder> orders = new ArrayList<>();
    private static final String logFile = "OrderLog.txt";
    private static final String inventoryFile = "Inventory.txt";

    /**
     * Main method
     *
     * @param args
     */

    public static void main(String[] args) {
        setInventory(inventoryFile);
        inventory = (readInventory(inventoryFile));
        System.out.println("Welcome to Java Coffee Co.!");
        try (Scanner input = new Scanner(System.in)) {
            boolean menu = true;
            String yn;
            int choice;
            while (menu) {
                System.out.print("""
                            1. New Order
                            2. Reload Inventory
                            3. Upload Inventory
                            4. Update Order Log
                            5. Exit Application
                        """);
                choice = input.nextInt();
                switch (choice) {
                    case 1 -> {
                        CoffeeOrder order = buildOrder();
                        orders.add(order);
                        String s;
                        System.out.println(order.printOrder());
                    }
                    case 2 -> {
                        inventory = (readInventory(inventoryFile));
                        printInventory(inventory);
                    }
                    case 3 -> writeInventory(inventoryFile);
                    case 4 -> writeOrderLog(logFile);
                    case 5 -> {
                        if (orders.size() > 0) writeOrderLog(logFile);
                        writeInventory(inventoryFile);
                        System.exit(0);
                    }
                    default -> {
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        writeOrderLog(logFile);
    }

    /**
     *
     * Method to create each order
     * No parameter
     * @return returns instance of CoffeeOrder
     * @throws Exception e
     */
    private static CoffeeOrder buildOrder() {
        CoffeeOrder order = new CoffeeOrder();
        try {
            Scanner input = new Scanner(System.in);
            boolean addCoffee = true;
            while (addCoffee) {
                // prompt user to select base coffee type
                System.out.println("Select coffee type:");
                System.out.println("\t1. Black Coffee");
                System.out.println("\t2. Espresso");
                Coffee coffee;

                int option = 0;
                while (option < 1 || option > 2) {
                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    } else {
                        option = input.nextInt();
                        if (option < 1 || option > 2) System.out.println("Please enter a valid option.");
                    }
                }
                input.nextLine(); // nextInt() doesn't consume newline
                if (option == 2) {
                    // Espresso is a specific case
                    if (isInInventory("Espresso")) {
                        coffee = new Espresso();
                        inventory.replace("Espresso", inventory.get("Espresso") - 1);
                    }
                    else {
                        System.out.println("No Espresso is left");
                        break;
                    }
                } else {
                    // make BlackCoffee the default case
                    if (isInInventory("Black Coffee")) {
                        coffee = new BlackCoffee();
                        inventory.replace("Black Coffee", inventory.get("Black Coffee") - 1); // subtract 1
                    }
                    else {
                        System.out.println("No Black Coffee is left");
                        break;
                    }
                }

                // prompt user for any customizations
                while (option != 0) {
                    System.out.printf("Coffee brewing: %s.%n", coffee.printCoffee());
                    System.out.println("Would you like to add anything to your coffee?");
                    System.out.println("\t1. Flavored Syrup");
                    System.out.println("\t2. Hot Water");
                    System.out.println("\t3. Milk");
                    System.out.println("\t4. Sugar");
                    System.out.println("\t5. Whipped Cream");
                    System.out.println("\t0. NO - Finish Coffee");

                    while (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    }
                    option = input.nextInt();
                    input.nextLine();

                    switch (option) {
                        case 1 -> {
                            System.out.println("Please select a flavor:");
                            for (WithFlavor.Syrup flavor : WithFlavor.Syrup.values()) {
                                System.out.println("\t" + String.format("%d. %s", flavor.ordinal() + 1, flavor));
                            }
                            int max = WithFlavor.Syrup.values().length;
                            option = 0;
                            while (option < 1 || option > max) {
                                if (!input.hasNextInt()) {
                                    System.out.println("Please enter a valid number.");
                                    input.nextLine();
                                } else {
                                    option = input.nextInt();
                                    if (option < 1 || option > max) System.out.println("Please enter a valid option.");
                                }
                            }
                            input.nextLine();
                            WithFlavor.Syrup flavor = WithFlavor.Syrup.values()[option - 1];
                            option = 1;
                            coffee = new WithFlavor(coffee, flavor);
                        }
                        case 2 -> {
                            if (isInInventory("Hot Water")) {
                                coffee = new WithHotWater(coffee);
                                inventory.replace("How Water", inventory.get("How Water") - 1);
                            }
                            else {
                                System.out.println("No more Hot Water");
                                option = input.nextInt();
                            }
                        }
                        case 3 -> {
                            if (isInInventory("Milk")) {
                                coffee = new WithMilk(coffee);
                                inventory.replace("Milk", inventory.get("Milk") - 1);
                            }
                            else {
                                option = input.nextInt();
                                System.out.println("No more Milk");
                            }
                        }
                        case 4 -> {
                            if (isInInventory("Sugar")) {
                                coffee = new WithSugar(coffee);
                                inventory.replace("Sugar", inventory.get("Sugar") - 1);
                            }
                            else {
                                option = input.nextInt();
                                System.out.println("No more Sugar");
                            }
                        }
                        case 5 -> {
                            if (isInInventory("Whipped Cream")) {
                                coffee = new WithWhippedCream(coffee);
                                inventory.replace("Whipped Cream", inventory.get("Whipped Cream") - 1);
                            }
                            else {
                                option = input.nextInt();
                                System.out.println("No more Whipped Cream");
                            }
                        }
                        default -> {
                            if (option != 0) System.out.println("Please enter valid option.");
                        }
                    }
                }
                order.addCoffee(coffee);

                System.out.println("""
                Please select a following option...
                1. Order another coffee
                2. Checkout
                    0. Apply discount""");
                int next = input.nextInt();

                if (next != 1)
                    addCoffee = false;

                if (next == 0)
                    discountPrice(order);
            }
        } catch (Exception e) {
            System.out.println("Error building order: " + e.getMessage());
        }
        return order;
    }
    /**
     * Reads inventory from file to collect ingredients and quantities of each
     * @param filePath which is called as inventoryFile
     * @return TreeMap<String, Integer>
     * @throws Exception e
     */
    private static Map<String, Integer> readInventory(String filePath) {
        Map<String, Integer> method = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                String [] s = line.split(" = ");
                int val = Integer.parseInt(s[1]);
                method.put(s[0], val);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error reading inventory: " + e.getMessage());
        }
        return method;
    }

    /**
     * Helper method that sets the inventory to model the sample inventory that is given
     * @param filePath which is called as inventoryFile
     * @throws Exception e
     */
    private static void setInventory(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Black Coffee = 10\n");
            writer.write("CARAMEL Syrup = 5\n");
            writer.write("Espresso = 10\n");
            writer.write("Hot Water = 100\n");
            writer.write("Milk = 10\n");
            writer.write("MOCHA Syrup = 5\n");
            writer.write("Sugar = 10\n");
            writer.write("VANILLA Syrup = 5\n");
            writer.write("Whipped Cream = 5\n");
        } catch (Exception e) {
            System.out.println("Error writing inventory: " + e.getMessage());
        }
    }

    /**
     * Writes inventory to Inventory.txt from inventory TreeMap
     * @param filePath which is called as inventoryFile
     * @throws Exception e
     */
    private static void writeInventory(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String s : inventory.keySet()) {
                writer.write(s + " = " + inventory.get(s));
                writer.newLine();
            }
            System.out.println("Inventory successfully written!");
        } catch (Exception e) {
            System.out.println("Error writing inventory: " + e.getMessage());
        }
    }

    /**
     * Writes order receipts to OrderLog.txt from List orders
     * @param filePath which is called as inventoryFile
     * @throws Exception e
     */
    private static void writeOrderLog(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (CoffeeOrder order : orders) {
                writer.write(order.printOrder());
                writer.newLine();
            }
            orders.clear();
            System.out.println("Order log successfully written!");
        } catch (Exception e) {
            System.out.println("Error writing order log: " + e.getMessage());
        }
    }

    /**
     * Takes in String key, finds if key exists and checks if value corresponding is greater than 0
     * @param i that is the String at the key of inventory TreeMap
     * @return boolean true/false
     */
    private static boolean isInInventory(String i) {
        for (String s : inventory.keySet()) {
            if (s.equals(i) && (inventory.get(s) > 0)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method that prints inventory to easily view values in TreeMap
     */
    private static void printInventory(Map<String, Integer> inventory) {
        for (String s : inventory.keySet()) {
            System.out.println(s + " = " + inventory.get(s));
        }
    }

    /**
     * Discount price gives options for discounts
     * @param order
     */
    private static void discountPrice(CoffeeOrder order) {
        Scanner scan = new Scanner(System.in);
        int choice;
        System.out.println("""
                DISCOUNT OPTIONS
                    1. Police/Firefighter
                    2. Military
                    3. Student
                    4. Employee""");
        choice = scan.nextInt();

        order.setDiscount(choice);
        order.toDiscount();
    }
}