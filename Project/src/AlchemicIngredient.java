import java.lang.reflect.Array;

public class AlchemicIngredient {

    static String name;
    static IngredientType ingredientType;
    static State state;
    static int quantity;
    static Array temperature;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        AlchemicIngredient.name = name;
    }

    public static IngredientType getIngredientType() {
        return ingredientType;
    }

    public static void setIngredientType(IngredientType ingredientType) {
        AlchemicIngredient.ingredientType = ingredientType;
    }

    public static State getState() {
        return state;
    }

    public static void setState(State state) {
        AlchemicIngredient.state = state;
    }

    public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        AlchemicIngredient.quantity = quantity;
    }

    public static Array getTemperature() {
        return temperature;
    }

    public static void setTemperature(Array temperature) {
        AlchemicIngredient.temperature = temperature;
    }
}
