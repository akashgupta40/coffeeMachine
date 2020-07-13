import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    private final static CoffeeMachine coffeeMachine = new CoffeeMachine(10);

    public static void main(String[] args)  {

         // adding ingredients
//        coffeeMachine.addIngredient(Ingredient.hot_water, 2000L);
//        coffeeMachine.addIngredient(Ingredient.hot_milk, 2000L);
//        coffeeMachine.addIngredient(Ingredient.tea_leaves_syrup, 2000L);
//        coffeeMachine.addIngredient(Ingredient.ginger_syrup, 25L);
//        coffeeMachine.addIngredient(Ingredient.sugar_syrup, 2000L);
//
//        coffeeMachine.generateBeverage(Beverage.hot_tea);
//        coffeeMachine.generateBeverage(Beverage.hot_tea);
//        coffeeMachine.generateBeverage(Beverage.hot_tea);
//        coffeeMachine.generateBeverage(Beverage.hot_tea);
//        coffeeMachine.generateBeverage(Beverage.hot_tea);
//        coffeeMachine.generateBeverage(Beverage.hot_tea);
//        coffeeMachine.generateBeverage(Beverage.hot_tea);
//        coffeeMachine.generateBeverage(Beverage.hot_tea);
//        coffeeMachine.generateBeverage(Beverage.hot_tea);
//        coffeeMachine.generateBeverage(Beverage.hot_tea);

        functionalTest1();
        functionalTest2();
        functionalTest3();
        functionalTest4();


    }

    private static void functionalTest1() {
        System.out.println("TestCase1 : When ingredient quantity is not sufficient for some beverage ");
        try {
            CoffeeMachine coffeeMachine = new CoffeeMachine(3);
            coffeeMachine.addIngredient(Ingredient.hot_water, 500L);
            coffeeMachine.addIngredient(Ingredient.hot_milk, 500L);
            coffeeMachine.addIngredient(Ingredient.ginger_syrup, 100L);
            coffeeMachine.addIngredient(Ingredient.sugar_syrup, 100L);
            coffeeMachine.addIngredient(Ingredient.tea_leaves_syrup, 100L);
            CompletableFuture<Boolean> output = coffeeMachine.generateBeverage(Beverage.hot_tea);
            assert (output.get());
            output = coffeeMachine.generateBeverage(Beverage.hot_tea);
            assert (output.get());
            output = coffeeMachine.generateBeverage(Beverage.hot_tea);
            assert (!output.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void functionalTest2() {
        System.out.println("\nTestCase2 : When ingredient quantity is not available for some beverage ");
        try {
            CoffeeMachine coffeeMachine = new CoffeeMachine(3);
            coffeeMachine.addIngredient(Ingredient.hot_water, 400L);
            coffeeMachine.addIngredient(Ingredient.hot_milk, 500L);
            coffeeMachine.addIngredient(Ingredient.ginger_syrup, 100L);
            coffeeMachine.addIngredient(Ingredient.sugar_syrup, 100L);
            coffeeMachine.addIngredient(Ingredient.tea_leaves_syrup, 100L);
            CompletableFuture<Boolean> output = coffeeMachine.generateBeverage(Beverage.hot_tea);
            assert (output.get());
            output = coffeeMachine.generateBeverage(Beverage.hot_tea);
            assert (output.get());
            output = coffeeMachine.generateBeverage(Beverage.hot_tea);
            assert (!output.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    private static void functionalTest3() {
        System.out.println("\nTestCase3 : when ingredient is below alarmLevel ");
        try {
            CoffeeMachine coffeeMachine = new CoffeeMachine(3);
            coffeeMachine.addIngredient(Ingredient.hot_water, 205L);
            coffeeMachine.addIngredient(Ingredient.hot_milk, 500L);
            coffeeMachine.addIngredient(Ingredient.ginger_syrup, 100L);
            coffeeMachine.addIngredient(Ingredient.sugar_syrup, 100L);
            coffeeMachine.addIngredient(Ingredient.tea_leaves_syrup, 100L);
            CompletableFuture<Boolean> out = coffeeMachine.generateBeverage(Beverage.hot_tea);
            out.get();
            Boolean output = coffeeMachine.inAlaramedState(Ingredient.hot_water);
            assert (output);
        } catch (Exception e) {

        }
    }

    private static void functionalTest4() {
        System.out.println("\nTestCase4 : When some ingredient is not available at all ");
        try {
            CoffeeMachine coffeeMachine = new CoffeeMachine(3);
            coffeeMachine.addIngredient(Ingredient.hot_milk, 500L);
            coffeeMachine.addIngredient(Ingredient.ginger_syrup, 100L);
            coffeeMachine.addIngredient(Ingredient.sugar_syrup, 100L);
            coffeeMachine.addIngredient(Ingredient.tea_leaves_syrup, 100L);
            CompletableFuture<Boolean> output = coffeeMachine.generateBeverage(Beverage.hot_tea);
            assert (!output.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    }
