import java.util.List;

public class CoffeeDecorator implements Coffee {
    Coffee coffee;

    public CoffeeDecorator(Coffee c) {
        coffee = c;
    }
    @Override
    public String printCoffee() {
        return coffee.printCoffee();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }

    @Override
    public List<String> getIngredients() {
        return coffee.getIngredients();
    }
}
