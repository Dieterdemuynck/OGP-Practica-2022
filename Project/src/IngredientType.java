public class IngredientType {

    private static String name;
    private static long standardTemperature;
    private State standardState;

    /* *********************************************************
     * CONSTRUCTORS
     * *********************************************************/
    public IngredientType(String name, long[] standardTemperature){
        setName(name);
        setStandardTemperature(standardTemperature);
    }

    public IngredientType(String name, long[] standardTemperature, State state){
        setName(name);
        setStandardTemperature(standardTemperature);
    }

    //IK VIND HET VREEMD DAT ER GEEN STATE MEEGEGEVEN WORDT -> HOE WORDT DIT DAN INGEVULT?

    /* *********************************************************
     * NAME
     * *********************************************************/
    public static String getName() {
        return name;
    }

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
            throw new IllegalTemperatureException(standardTemperature); // want standardtemperature > [0,0]
        }
        else{
            if (standardTemperature[1] != 0){
                temp = standardTemperature[1];
            }
            else {
                throw new IllegalTemperatureException(standardTemperature); // want standardtemperature > [0,0]
            }
        }
        IngredientType.standardTemperature = temp;
    }

    private static void setStandardTemperature(int temperature) { // input: int -> private
        IngredientType.standardTemperature = temperature;
    }

    private static boolean isValidTemperature(long[] standardTemperature) {
        if (standardTemperature[0] != 0 && standardTemperature.length == 2){
            return standardTemperature[1] == 0 && standardTemperature[0] <= AlchemicIngredient.MAX_TEMPERATURE ;
        }
        else return  standardTemperature.length == 2 && standardTemperature[1] <= AlchemicIngredient.MAX_TEMPERATURE ;
    }

    /* *********************************************************
     * STANDARD STATE
     * *********************************************************/

    public State getStandardState() {
        return standardState;
    }

    private void setStandardState(State standardState) {
        this.standardState = standardState;
    }

    /* *********************************************************
     * OTHER
     * *********************************************************/
    public boolean isMixedIngredient(AlchemicIngredient alchemicIngredient) {
        return false;
    }
}
