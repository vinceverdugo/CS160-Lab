/**
 * Vince Verdugo
 * CS160L
 */
import java.util.List;
public class WithMilk extends CoffeeDecorator{
    public WithMilk(Coffee c) {
        super(c);
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with milk";
    }
    @Override
    public double getCost(){
        return super.getCost() + 0.55;
    }
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add("Milk");
        return ingredients;
    }
}
