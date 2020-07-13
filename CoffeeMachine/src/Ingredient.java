public enum Ingredient {
    hot_water(10L),
    hot_milk(10L),
    tea_leaves_syrup(10L),
    ginger_syrup(10L),
    sugar_syrup(10L),
    elaichi_syrup(10L),
    green_mixture(10L),
    coffee_syrup(10L);

    public final Long alarmLevel;

    Ingredient(Long alarmLevel) {
        this.alarmLevel = alarmLevel;
    }
}
