public class IngredientType {

    private final String name;
    private final long standardTemperature;
    private final State standardState;

    /* *********************************************************
     * CONSTRUCTORS
     * *********************************************************/
    public IngredientType(String name, long[] standardTemperature, State standardState){
        if (!isValidName(name)){
            throw new IllegalNameException(name);
        }
        this.name = name;
        if (!isValidStandardTemperature(standardTemperature)) {
            throw new IllegalTemperatureException(standardTemperature);
        }
        this.standardTemperature = standardTemperature[1];
        this.standardState = standardState;
    }


    /* *********************************************************
     * NAME
     * *********************************************************/
    public String getName() {
        return name;
    }

    private static boolean isValidName(String name){
        return true; //TODO Dieter =)
    }

    /* *********************************************************
     * TEMPERATURE
     * *********************************************************/

    public long[] getStandardTemperature() {
        long[] temp = new long[2];
        if (this.standardTemperature < 0){
            temp[0] = Math.abs(this.standardTemperature);
        }
        else{
            temp[1] = this.standardTemperature;
        }
        return temp;
    }

    private static boolean isValidStandardTemperature(long[] standardTemperature) {
        return standardTemperature[0] == 0 && standardTemperature[1] != 0
                && standardTemperature[1] <= AlchemicIngredient.MAX_TEMPERATURE && standardTemperature.length == 2;
    }

    /* *********************************************************
     * STANDARD STATE
     * *********************************************************/

    public State getStandardState() {
        return standardState;
    }
    /* *********************************************************
     * OTHER
     * *********************************************************/
    public boolean isMixedIngredient(AlchemicIngredient alchemicIngredient) {
        return false;
    }
}
