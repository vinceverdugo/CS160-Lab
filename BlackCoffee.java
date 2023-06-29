/**
 * Vince Verdugo
 * CS160L
 */
import java.util.ArrayList;
import java.util.List;

public class BlackCoffee implements Coffee {
    public BlackCoffee() {}
    @Override
    public String printCoffee() {
        return "A black coffee";
    }

    @Override
    public double getCost() {
        return 1.0;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Black Coffee");
        return ingredients;
    }
}
