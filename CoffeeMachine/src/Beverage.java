import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Beverage {

    hot_tea {
        @Override
        public List<Component> getComponents() {
            final List<Component> components = new ArrayList<>();
            components.add(new Component(Ingredient.hot_water,200L));
            components.add(new Component(Ingredient.hot_milk, 100L));
            components.add(new Component(Ingredient.tea_leaves_syrup, 30L));
            components.add(new Component(Ingredient.ginger_syrup, 10L));
            components.add(new Component(Ingredient.sugar_syrup, 10L));
            return components;
        }
    },
    hot_coffee {
        @Override
        public List<Component> getComponents() {
            final List<Component> components = new ArrayList<>();
            components.add(new Component(Ingredient.hot_water,100L));
            components.add(new Component(Ingredient.ginger_syrup, 30L));
            components.add(new Component(Ingredient.hot_milk, 400L));
            components.add(new Component(Ingredient.tea_leaves_syrup, 30L));
            components.add(new Component(Ingredient.sugar_syrup, 50L));
            return components;
        }
    },
    black_tea {
        @Override
        public List<Component> getComponents() {
            final List<Component> components = new ArrayList<>();
            components.add(new Component(Ingredient.hot_water,300L));
            components.add(new Component(Ingredient.ginger_syrup, 30L));
            components.add(new Component(Ingredient.tea_leaves_syrup, 30L));
            components.add(new Component(Ingredient.sugar_syrup, 50L));
            return components;
        }
    },
    green_tea {
        @Override
        public List<Component> getComponents() {
            final List<Component> components = new ArrayList<>();
            components.add(new Component(Ingredient.hot_water,100L));
            components.add(new Component(Ingredient.ginger_syrup, 30L));
            components.add(new Component(Ingredient.sugar_syrup, 50L));
            components.add(new Component(Ingredient.green_mixture, 30L));
            return components;
        }
    },
    hot_water {
        @Override
        public List<Component> getComponents() {
            final List<Component> components = new ArrayList<>();
            components.add(new Component(Ingredient.hot_water,50L));
            return components;
        }
    };

    public abstract List<Component> getComponents();
}

