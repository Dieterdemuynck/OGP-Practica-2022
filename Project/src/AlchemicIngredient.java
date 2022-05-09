import java.lang.reflect.Array;

public class AlchemicIngredient {

    static String name;
    static IngredientType ingredientType;
    static State state;
    static int quantity;
    static int degree    ;

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

    public static int[] degreeToTemp(int degree) {
        int[] temperature = {0, 0};
        if (degree < 0) {
            temperature[0] = degree * -1;
        } else {
            temperature[1] = degree;
        }
        return temperature;
    }

    public static int getDegree() {
        return degree;
    }

    public static void setDegree(int degree) {
        AlchemicIngredient.degree = degree;
    }

    public static int[] getTemperature() {
        int[] temperature = degreeToTemp(AlchemicIngredient.getDegree());
        return temperature;
    }

    public static void setTemperature(int[] temperature) {
        if (temperature[0] == 0) {setDegree(temperature[1]);}
        else {setDegree(-temperature[0]);}
    }
}
