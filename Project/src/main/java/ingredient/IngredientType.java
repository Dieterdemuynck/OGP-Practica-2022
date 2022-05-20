package main.java.ingredient;

import main.java.ingredient.exception.IllegalNameException;
import main.java.ingredient.exception.NonMixedSpecialNameException;

import java.util.ArrayList;
import java.util.List;

/**
 * A class of ingredient types, can only be summoned by alchemicIngredient
 * @author Dieter Demuynck, Hannes Ingelaere and Ine Malfait
 * @version 1.0
 */

public class IngredientType {

    /**
     * Variable registering the name of this ingredient type.
     */
    private final String name;
    /**
     * Variable registering the standard temperature of this ingredient type.
     */
    private final long standardTemperature;
    /**
     * Variable referencing the standard state of this ingredient type.
     */
    private final State standardState;

    /* *********************************************************
     * CONSTRUCTORS
     * *********************************************************/

    /**
     * Initialize a new ingredient type with given name, standard temperature and standard state.
     *
     * @param   name
     *          The name of the new ingredient type
     * @param   standardTemperature
     *          the standard temperature of the new ingredient type
     * @param   standardState
     *          the standard state of the new ingredient type
     *
     * @pre     name is a valid name
     *          | isValidName(name)
     * @pre     standardTemperature is a valid standard temperature
     *          | isValidStandardTemperature(standardTemperature)
     * @pre     standardState is a valid standard state
     *          | isValidState(standardState)
     * @post    The name of this new ingredient type is set to the given name
     * @post    The standard temperature of this new ingredient type is set to the given standardTemperature
     * @post    The standard state of this new ingredient type is set to the given standardState
     */
    public IngredientType(String name, long[] standardTemperature, State standardState){
        if (!isValidName(name)){
            throw new IllegalNameException(name);
        }
        this.name = name;
        if (!isValidStandardTemperature(standardTemperature)) {
            this.standardTemperature = 20;
        }
        else {
            this.standardTemperature = standardTemperature[1];
        }
        this.standardState = standardState;
    }

    protected IngredientType( long[] standardTemperature, State standardState){
        this.name = null;
        if (!isValidStandardTemperature(standardTemperature)) {
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

    /**
     * Return the name of this ingredient type
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether the given name is a valid simple name (NOT MIXED!)
     *
     * @param   name
     *          The string representation of the new name of a new ingredient.
     * @return  True if and only if name is a valid simple name, false otherwise.
     */
    public static boolean isValidName(String name){
        return name.matches("([A-Z][a-z']+( [A-Z][a-z']+)+|[A-Z][a-z']{2,})");
    }

    /**
     * Returns false because a (not mixed) ingredient type doesn't have a special name
     */
    public boolean hasSpecialName(){
        return false;
    }

    public List<String> getComponentNames(){
        List<String> componentNames = new ArrayList<>();
        componentNames.add(getName());
        return componentNames;
    }

    /* *********************************************************
     * SPECIAL NAME
     * *********************************************************/

    /**
     * Doesn't set the special name to the given special name, because ingredient type is not mixed
     * @param   specialName
     *          The special name
     * @throws NonMixedSpecialNameException(this)
     *          The ingredient type is not a mixed one.
     */
    public void setSpecialName(String specialName) {
        throw new NonMixedSpecialNameException(this);
    }

    /**
     * Returns null because an ingredient type is not mixed and doesn't have a special name
     */
    public String getSpecialName(){
        return null;
    }

    /* *********************************************************
     * TEMPERATURE
     * TOTAAL
     * *********************************************************/

    /**
     * Returns the standard temperature of this ingredient type
     */
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

    /**
     * Checks whether the standard temperature is valid
     * @param   standardTemperature
     *          the standard temperature to check
     * @return  True if and only if the coolness is 0 and the hotness is above 0 and below the MAX_TEMPERATURE
     *          | result == (standardTemperature[0] == 0 and standardTemperature[1] != 0
     *                 and standardTemperature[1] <= AlchemicIngredient.MAX_TEMPERATURE)
     */
    private static boolean isValidStandardTemperature(long[] standardTemperature) {
        return standardTemperature[0] == 0 && standardTemperature[1] != 0
                && standardTemperature[1] <= AlchemicIngredient.MAX_TEMPERATURE && standardTemperature.length == 2;
    }

    /* *********************************************************
     * STANDARD STATE
     * ?
     * *********************************************************/

    /**
     * Return the standard state of this ingredient type
     */
    public State getStandardState() {
        return standardState;
    }

    /**
     * Checks whether this standard state is valid
     * @param   standardState
     *          the standard state to check
     * @return  True if and only if this standard state is not null
     *          | result == (standardState != null)
     */
    public static boolean isValidState(State standardState){
        return standardState != null;
    }
    /* *********************************************************
     * OTHER
     * ?
     * *********************************************************/

    /**
     * Returns false because an ingredient type is not mixed.
     */
    public boolean isMixedIngredient() {
        return false;
    }
}
