package main.java.ingredient;

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
        if (!isValidStandardTemperature(standardTemperature)) { //MOET TOTAAL ZIJN, dus geen exceptions
            this.standardTemperature = 20;
        }
        else {
            this.standardTemperature = standardTemperature[1];
        }
        this.standardState = standardState;
    }


    /* *********************************************************
     * NAME
     * DEFENSIEF
     * *********************************************************/
    public String getName() {
        return name;
    }

    /**
     * Returns whether the given name is a valid simple name (NOT MIXED!)
     *
     * @param name  The string representation of the new name of a new ingredient.
     * @return      true if and only if name is a valid simple name, false otherwise.
     */
    public static boolean isValidName(String name){
        return name.matches("([A-Z][a-z']+( [A-Z][a-z']+)+|[A-Z][a-z']{2,})"); //TODO Dieter =)
    }

    public boolean hasSpecialName(){
        return false;
    }

    /* *********************************************************
     * TEMPERATURE
     * TOTAAL
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
     * ?
     * *********************************************************/

    public State getStandardState() {
        return standardState;
    }
    /* *********************************************************
     * OTHER
     * ?
     * *********************************************************/
    public boolean isMixedIngredient() {
        return false;
    }
}
