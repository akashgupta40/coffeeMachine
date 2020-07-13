public enum IngredientStatus {
    AVAILABLE("available"),
    NOT_AVAILABLE("not available"),
    NOT_SUFFICIENT("not sufficient");

    public final String displayName;

    IngredientStatus(String displayName) {
        this.displayName = displayName;
    }

}
