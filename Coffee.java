import java.util.List;
interface Coffee {
    /**
     * Will print coffee order
     * @return String
     */
    String printCoffee();

    /**
     * Collects cost of item
     * @return double
     */
    double getCost();

    /**
     * Gets list of ingredients, added on by each addition to the coffee
     * @return List<String>
     */
    List<String> getIngredients();
}
