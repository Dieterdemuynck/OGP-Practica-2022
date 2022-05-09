public class IngredientType {

    private static String name;
    private static long standardTemperature;
    private State state;

    /* *********************************************************
     * CONSTRUCTORS
     * *********************************************************/
    public IngredientType(String name, long[] standardTemperature){
        setName(name);
        setStandardTemperature(standardTemperature);

    }


    public static String getName() {
        return name;
    }

    public static String getFullName() {
        return name;
    } //TODO: juist zetten

    public static void setName(String name) {
        if (isValidName(name)){
            IngredientType.name = name;
        }
    }

    private static boolean isValidName(String name){
        return true; //TODO Dieter =)
    }

    public static long[] getStandardTemperature() {
        long[] temp = new long[2];
        if (IngredientType.standardTemperature < 0){
            temp[0] = Math.abs(IngredientType.standardTemperature);
        }
        else{
            temp[1] = IngredientType.standardTemperature;
        }
        return temp;
    }

    public static void setStandardTemperature(long[] standardTemperature) throws IllegalTemperatureException { // input : array
        if (!isValidTemperature(standardTemperature)) {
            throw new IllegalTemperatureException(standardTemperature);
        }
        long temp = 0;
        if (standardTemperature[0] != 0) {
            temp -= standardTemperature[0];
        }
        else{
            temp = standardTemperature[1];
        }
        IngredientType.standardTemperature = temp;
    }

    private static void setTemperature(int temperature) { // input: int -> private
        IngredientType.standardTemperature = temperature;
    }

    private static boolean isValidTemperature(long[] standardTemperature) {
        if (standardTemperature[0] != 0 && standardTemperature.length == 2){
            return standardTemperature[1] == 0 && standardTemperature[0] <= AlchemicIngredient.MAX_TEMPERATURE ;
        }
        else return  standardTemperature.length == 2 && standardTemperature[1] <= AlchemicIngredient.MAX_TEMPERATURE ;
    }

    public boolean isMixedIngredient(AlchemicIngredient alchemicIngredient) {
        return false;
    }
}
