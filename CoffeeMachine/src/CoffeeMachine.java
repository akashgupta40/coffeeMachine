import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoffeeMachine {

    private final IngredientStore ingredientStore;
    private ExecutorService pool;

    public CoffeeMachine(int noOfOutlets) {
        pool = Executors.newFixedThreadPool(Math.min(100,noOfOutlets)) ;
        this.ingredientStore = new IngredientStore();
    }

    /**
     * adds ingredient of specified quantity in coffee machine
     * @param ingredient
     * @param quantity
     */
    public void addIngredient(Ingredient ingredient, Long quantity) {
        ingredientStore.addIngredient(ingredient, quantity);
    }

    /**
     * entry function to generate beverage
     * @param beverage
     * @throws InterruptedException
     * @throws ExecutionException
     * @return
     */
    public CompletableFuture<Boolean> generateBeverage(Beverage beverage)  {
        return CompletableFuture
            .supplyAsync(() -> generateDrinkAtOutlet(beverage), pool);
    }

    /**
     * driver function to generate beverage, reserves ingredients and then process
     * @param beverage
     * @return
     */
    private Boolean generateDrinkAtOutlet(Beverage beverage) {
        // reserve all ingredients
        List<Component> reservedComponents = Collections.synchronizedList(new ArrayList<>());
        List<Component> componentsRequired = beverage.getComponents();
        IngredientStatus ingredientStatus = IngredientStatus.AVAILABLE;
        Component unavailableComponent = null;
        for (Component component: componentsRequired) {
            ingredientStatus = ingredientStore.reserveIngredient(component);
            if (IngredientStatus.AVAILABLE.equals(ingredientStatus)) {
                reservedComponents.add(component);
            } else if (IngredientStatus.NOT_SUFFICIENT.equals(ingredientStatus)) {
                unavailableComponent = component;
                break;
            } else if (IngredientStatus.NOT_AVAILABLE.equals(ingredientStatus)) {
                unavailableComponent = component;
                break;
            }
        }

        // check if all ingredients are available
        if (reservedComponents.size() == componentsRequired.size()) {
            try {
                Thread.sleep(7000L);
            } catch (Exception e) {
                System.out.println("Error while running outlet");
            }
            System.out.println(String.format("%s is prepared", beverage.name()));
            return true;
        }

        // free up the ingredients acquired
        else {
            System.out.println(String.format("%s cannot be prepared because item %s is %s", beverage.name(), unavailableComponent.getIngredient(), ingredientStatus.displayName));
            CompletableFuture
                .supplyAsync(() -> ingredientStore.unreserveComponents(reservedComponents));
            return false;

        }
    }

    public Boolean inAlaramedState(Ingredient ingredient) {
        return ingredientStore.inAlaramedState(ingredient);
    }


}
