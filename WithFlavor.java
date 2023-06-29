import java.util.List;

public class WithFlavor extends CoffeeDecorator {

    enum Syrup {
        /**
         * Enum Syrup has variables that are assigned to the available flavors
         */
        CARAMEL,
        MOCHA,
        VANILLA
    }

    private Syrup flavor;

    public WithFlavor(Coffee c, Syrup s) {
        super(c);
        flavor = s;
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.35;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add(flavor + " syrup");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with " + flavor;
    }
}
