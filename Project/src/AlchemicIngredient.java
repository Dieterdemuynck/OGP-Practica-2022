import java.lang.reflect.Array;

public class AlchemicIngredient {

    static String name;
    static String ingredientType;
    static String state;
    static int quantity;
    static Array temperature;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        AlchemicIngredient.name = name;
    }

    public static String getIngredientType() {
        return ingredientType;
    }

    public static void setIngredientType(String ingredientType) {
        AlchemicIngredient.ingredientType = ingredientType;
    }

    public static String getState() {
        return state;
    }

    public static void setState(String state) {
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
