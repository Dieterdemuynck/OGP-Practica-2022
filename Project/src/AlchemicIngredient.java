public class AlchemicIngredient {

    static IngredientType ingredientType;
    static State state;
    static int quantity;
    static long temperature;
    public static final int MAX_TEMPERATURE = 10000;


    /* *********************************************************
     * CONSTRUCTORS
     * *********************************************************/
    public AlchemicIngredient(int quantity, int[] standardTemperature, String name){
        AlchemicIngredient.ingredientType = new IngredientType(name, standardTemperature);
        setQuantity(quantity);
    }

    public AlchemicIngredient(int quantity){
        int[] standardTemperature = {0, 20};
        AlchemicIngredient.ingredientType = new IngredientType("Water", standardTemperature);
        setQuantity(quantity);
    }

    /* *********************************************************
     * INGREDIENT TYPE
     * *********************************************************/
    public static IngredientType getIngredientType() {
        return ingredientType;
    }

    public static void setIngredientType(IngredientType ingredientType) {
        AlchemicIngredient.ingredientType = ingredientType;
    }

    public String getName(){
        return ingredientType.getName();
    }

    public String getFullName(){
        return ingredientType.getFullName();
    }

    public String getSpecialName(){
        if (ingredientType.isMixedIngredient(this)){
            return MixedIngredientType.getSpecialName();
        }
        else {
            return null;
        }
    }

    /* *********************************************************
     * STATE
     * *********************************************************/
    public static State getState() {
        return state;
    }

    public static void setState(State state) {
        AlchemicIngredient.state = state;
    }

    /* *********************************************************
     * QUANTITY
     * *********************************************************/
    public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        AlchemicIngredient.quantity = quantity;
    }

    /* *********************************************************
     * TEMPERATURE
     * *********************************************************/
    public static long[] getTemperature() {
        long[] temp = new long[2];
        if (AlchemicIngredient.temperature < 0) {
            temp[0] = Math.abs(AlchemicIngredient.temperature);
        } else {
            temp[1] = AlchemicIngredient.temperature;
        }
        return temp;
    }

    public static void setTemperature(long[] temperature) throws IllegalTemperatureException { // input : array
        if (!isValidTemperature(temperature)) {
            throw new IllegalTemperatureException(temperature);
        }
        long temp = 0;
        if (temperature[0] != 0) {
            temp -= temperature[0];
        } else {
            temp = temperature[1];
        }
        AlchemicIngredient.temperature = temp;
    }

    private static void setTemperature(long temperature) { // input: int -> private
        AlchemicIngredient.temperature = temperature;
    }

    private static boolean isValidTemperature(long[] temperature) {
        if (temperature[0] != 0 && temperature.length == 2) {
            return temperature[1] == 0 && temperature[0] <= MAX_TEMPERATURE;
        } else
            return temperature.length == 2 && temperature[1] <= MAX_TEMPERATURE; // dus temperature[0] == 0 -> array mag nog steeds maar 2 lang zijn en
    }

    public void heat(int temperature) {
        if (AlchemicIngredient.temperature + temperature <= MAX_TEMPERATURE) {
            AlchemicIngredient.temperature += temperature;
        }
    }

    public void cool(int temperature) {
        if (AlchemicIngredient.temperature - temperature >= -MAX_TEMPERATURE) {
            AlchemicIngredient.temperature += temperature;
        }
    }

}