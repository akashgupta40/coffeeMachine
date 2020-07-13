import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientStore {
    private final Map<Ingredient, Long> ingredientToQuantityyMap;

    public IngredientStore() {
        this.ingredientToQuantityyMap = new HashMap<>();
    }

    /**
     * reserves given quantity of ingredient
     * @param component
     * @return true if reserving ingredient is successful else returns false
     */
    public synchronized IngredientStatus reserveIngredient(Component component) {
        if (ingredientToQuantityyMap.getOrDefault(component.getIngredient(), 0L) <= 0L) {
            return IngredientStatus.NOT_AVAILABLE;
        } else if (ingredientToQuantityyMap.get(component.getIngredient()) < component.getQuantity()) {
            return IngredientStatus.NOT_SUFFICIENT;
        } else {
            ingredientToQuantityyMap.put(component.getIngredient(), ingredientToQuantityyMap.get(component.getIngredient()) - component.getQuantity());
            return IngredientStatus.AVAILABLE;
        }
    }

    /**
     * adds ingredient with the specified quantity in the store
     * @param ingredient
     * @param quantity
     * @return if addition takes place successfully
     */
    public synchronized Boolean addIngredient(Ingredient ingredient, Long quantity) {
        ingredientToQuantityyMap.putIfAbsent(ingredient, 0L);
        ingredientToQuantityyMap.put(ingredient, ingredientToQuantityyMap.get(ingredient)+quantity);
        return true;
    }

    public  Long unreserveComponents(List<Component> components) {
        components.stream().forEach(component -> addIngredient(component.getIngredient(), component.getQuantity()));
        return 1L;
    }

    public Boolean inAlaramedState(Ingredient ingredient) {
        return ingredientToQuantityyMap.getOrDefault(ingredient, 0L) <= ingredient.alarmLevel;
    }


}
