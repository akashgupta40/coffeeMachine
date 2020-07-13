public class Component {
    private final Ingredient ingredient;
    private final Long quantity;

    public Component(Ingredient ingredient, Long quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public Long getQuantity() {
        return quantity;
    }
}
