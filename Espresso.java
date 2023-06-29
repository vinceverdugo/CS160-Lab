/**
 * Vince Verdugo
 * CS160L
 */
import java.util.ArrayList;
import java.util.List;

public class Espresso implements Coffee {
    public Espresso() {}
    @Override
    public String printCoffee() {
        return "Espresso";
    }

    @Override
    public double getCost() {
        return 1.75;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Espresso");
        return ingredients;
    }
}
