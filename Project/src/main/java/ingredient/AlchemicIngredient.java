package main.java.ingredient;

import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
import main.java.ingredient.exception.IllegalNameException;
import main.java.ingredient.exception.NonMixedSpecialNameException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A class of alchemical ingredients
 * @author Dieter Demuynck, Hannes Ingelaere and Ine Malfait
 * @version 1.0
 */
public class AlchemicIngredient {

    /**
     * Variable referencing the ingredient type of this alchemical ingredient
     */
    private final IngredientType ingredientType;
    /**
     * Variable referencing the state of this alchemical ingredient
     */
    private final State state;
    /**
     * Variable registering the amount of this alchemical ingredient
     */
    private final int amount;
    /**
     * Variable referencing the unit of this alchemical ingredient
     */
    private final Unit unit;
    /**
     * Variable registering the temperature of this alchemical ingredient
     */
    private long temperature;
    /**
     * Variable registering the maximum temperature of any alchemical ingredient
     */
    public static final int MAX_TEMPERATURE = 10000;


    /* *********************************************************
     * CONSTRUCTORS
     * *********************************************************/

    // FOR IN TRANSMOGIFIER: CURRENT STATE AND TEMPERATURE
    /**
     * Initialize a new alchemical ingredient with given amount, unit, standard temperature, current temperature, name,
     * standard state and current state.
     *
     * @param   amount
     *          The amount of the new alchemical ingredient
     * @param   unit
     *          The unit of the new alchemical ingredient
     * @param   standardTemperature
     *          The standard temperature of the new alchemical ingredient
     * @param   currentTemperature
     *          The current temperature of the new alchemical ingredient
     * @param   name
     *          The name of the new alchemical ingredient
     * @param   standardState
     *          The standard state of the new alchemical ingredient
     * @param   currentState
     *          The current state of the new alchemical ingredient
     *
     * @pre     amount is a valid amount
     *          | isValidAmount(amount)
     * @pre     unit is a valid unit
     *          | isValidUnit(unit)
     * @pre     standardState is a valid state
     *          | isValidState(standardState)
     * @pre     currentState is a valid state
     *          | isValidState(currentState)
     * @effect  The new alchemical ingredient has an ingredient type with the given name, standardTemperature and
     *          standardState
     *          | IngredientType(name, standardTemperature, standardState)
     * @effect  The new alchemical ingredient has the given currentTemperature
     *          | setTemperature(currentTemperature)
     * @post    The amount of this new alchemical ingredient is set to the given amount
     *          | new.getAmount() == amount
     * @post    The unit of this new alchemical ingredient is set to the given unit
     *          | new.getUnit() == unit
     * @post    The state of this new alchemical ingredient is set to the given currentState
     *          | new.getState() == currentState
     */
    @Raw @Model
    public AlchemicIngredient(int amount, Unit unit, long[] standardTemperature, long[] currentTemperature, String name,
                              State standardState, State currentState) {
        this.ingredientType = new IngredientType(name, standardTemperature, standardState);
        this.amount = amount;

        // Only if the given unit is representative of a amount in the given state
        // can we allow an ingredient to be made
        if (!currentState.hasUnit(unit))
            throw new IllegalArgumentException();
        this.unit = unit;
        setTemperature(currentTemperature);
        this.state = currentState;
    }

    // FOR IN TRANSMOGIFIER: CURRENT STATE

    /**
     * Initialize a new alchemical ingredient with given amount, unit, standard temperature, name, standard state and
     * current state.
     *
     * @param   amount
     *          The amount of the new alchemical ingredient
     * @param   unit
     *          The unit of the new alchemical ingredient
     * @param   standardTemperature
     *          The standard temperature of the new alchemical ingredient
     * @param   name
     *          The name of the new alchemical ingredient
     * @param   standardState
     *          The standard state of the new alchemical ingredient
     * @param   currentState
     *          The current state of the new alchemical ingredient
     *
     * @effect  This new alchemical ingredient is initialized with the given amount, unit, standard temperature,
     *          name, standard state and current state.
     *          the new alchemical ingerdient has the standard temperature as temperature
     *          | this(amount, unit, standardTemperature, standardTemperature, name, standardState, currentState)
     */
    @Raw
    public AlchemicIngredient(int amount, Unit unit, long[] standardTemperature,
                              String name, State standardState, State currentState) {
        this(amount, unit, standardTemperature, standardTemperature, name, standardState, currentState);
    }

    // BASIC LONG CONSTRUCTOR

    /**
     * Initialize a new alchemical ingredient with given amount, unit, standard temperature, name and standard state.
     *
     * @param   amount
     *          The amount of the new alchemical ingredient
     * @param   unit
     *          The unit of the new alchemical ingredient
     * @param   standardTemperature
     *          The standard temperature of the new alchemical ingredient
     * @param   name
     *          The name of the new alchemical ingredient
     * @param   standardState
     *          The standard state of the new alchemical ingredient
     *
     * @effect  This new alchemical ingredient is initialized with the given amount, unit, standard temperature,
     *          name and standard state.
     *          the new alchemical ingerdient has the standard temperature as temperature and standard state as state
     *          | this(amount, unit, standardTemperature, standardTemperature, name, standardState, standardState)
     */
    @Raw @Model
    public AlchemicIngredient(int amount, Unit unit, long[] standardTemperature, String name, State standardState) {
        this(amount, unit, standardTemperature, standardTemperature, name, standardState, standardState);
    }

    // LONG CONSTRUCTOR WITHOUT UNIT - DEFAULT UNIT = SPOON

    /**
     * Initialize a new alchemical ingredient with given amount, standard temperature, name and standard state.
     * @param   amount
     *          The amount of the new alchemical ingredient
     * @param   standardTemperature
     *          The standard temperature of the new alchemical ingredient
     * @param   name
     *          The name of the new alchemical ingredient
     * @param   standardState
     *          The standard state of the new alchemical ingredient
     *
     * @effect  This new alchemical ingredient is initialized with the given amount, standard temperature, name and
     *          standard state.
     *          the new alchemical ingerdient has the standard temperature as temperature, standard state as state and
     *          Unit.Spoon as unit.
     *          | this(amount, Unit.Spoon, standardTemperature, name, standardState)
     */
    @Raw
    public AlchemicIngredient(int amount, long[] standardTemperature, String name, State standardState) {
        this(amount, Unit.Spoon, standardTemperature, name, standardState);
    }

    // BASIC SHORT CONSTRUCTOR

    /**
     * Initialize a new alchemical ingredient with given amount and unit.
     * @param   amount
     *          The amount of the new alchemical ingredient
     * @param   unit
     *          The unit of the new alchemical ingredient
     *
     * @effect  This new alchemical ingredient is initialized with the given amount and unit.
     *          the alchemic ingredient has the name "Water" and has a standard temperature (and temperature) of {0,20}
     *          and the standard state (and state) is State.Liquid.
     *          | this(amount, unit, new long[]{0, 20}, "Water", State.Liquid)
     */
    @Raw @Model
    public AlchemicIngredient(int amount, Unit unit) {
        this(amount, unit, new long[]{0, 20}, "Water", State.Liquid);
    }

    // SHORT CONSTRUCTOR WITHOUT UNIT - DEFAULT UNIT = SPOON

    /**
     * Initialize a new alchemical ingredient with given amount.
     * @param   amount
     *          The amount of the new alchemical ingredient
     *
     *
     * @effect  This new alchemical ingredient is initialized with the given amount.
     *          the alchemic ingredient has the name "Water" and has a standard temperature (and temperature) of {0,20},
     *          the standard state (and state) is State.Liquid and Unit.Spoon as unit
     *          | this(amount, unit, new long[]{0, 20}, "Water", State.Liquid)
     */
    @Raw
    public AlchemicIngredient(int amount) {
        this(amount, Unit.Spoon);
    }

    /**
     * Initialize a new (mixed) alchemica ingredient with given mixed ingredient type, amount, unit, current temperature
     * and current state.
     *
     * @param   mixedIngredientType
     *          The mixed ingredient type of the new alchemical ingredient
     * @param   amount
     *          the amount of the new alchemical ingredient
     * @param   unit
     *          the unit of the new alchemical ingredient
     * @param   currentTemperature
     *          the current temperature of the new alchemical ingredient
     * @param   currentState
     *          the current state of the new alchemical ingredient
     *
     * @pre     mixedIngredientType is a valid ingredient type
     *          | isValidIngredientType(mixedIngredientType)
     * @pre     amount is a valid amount
     *          | isValidAmount(amount)
     * @pre     unit is a valid unit
     *          | isValidUnit(unit)
     * @pre     currentState is a valid state
     *          | isValidState(standardState)
     * @effect  The new alchemical ingredient has the given currentTemperature
     *          | setTemperature(currentTemperature)
     * @post    The ingredient type of this new alchemical ingredient is set to the given mixed ingredient type
     *          | new.getIngredientType() == mixedIngredientType
     * @post    The amount of this new alchemical ingredient is set to the given amount
     *          | new.getAmount() == amount
     * @post    The unit of this new alchemical ingredient is set to the given unit
     *          | new.getUnit() == unit
     * @post    The state of this new alchemical ingredient is set to the given currentState
     *          | new.getState() == currentState
     */
    @Raw @Model
    private AlchemicIngredient(MixedIngredientType mixedIngredientType, int amount, Unit unit, long[] currentTemperature,
                               State currentState) {
        this.ingredientType = mixedIngredientType;
        this.amount = amount;
        if (! currentState.hasUnit(unit)) {
            throw new IllegalArgumentException();
        }
        this.unit = unit;
        setTemperature(currentTemperature);
        this.state = currentState;
    }

    /* *********************************************************
     * INGREDIENT TYPE
     * TOTAAL
     * *********************************************************/

    /**
     * Returns the ingredient type of this alchemical ingredient.
     */
    public IngredientType getIngredientType() {
        return ingredientType;
    }

    /**
     * Return whether the given ingredient type is a valid ingredient type for an alchemical ingredient.
     *
     * @param   ingredientType
     *          The ingredient type to check.
     * @return  True if and only if the given ingredient type is effective
     *          | result == (ingredientType != null)
     */
    public boolean isValidIngredientType(IngredientType ingredientType){
        return ingredientType != null;
    }

    /* ***************************
     * INGREDIENT TYPE - NAME
     * DEFENSIEF
     * ***************************/

    /**
     * Returns the name of this alchemical ingredient. The name is part of the ingredient type.
     * @return  The name of the alchemical ingredient.
     *          | ingredientType.getName()
     */
    public String getName() {
        return ingredientType.getName();
    }

    /**
     * Returns the full name of this alchemical ingredient.
     *
     * @return  If this is a mixed ingredient with a special name, the special name is returned preceded with a '(', the
     *          pre name, the name, the post name and a ')', otherwise the pre name is returned preceded with the name
     *          and the post name.
     *          | if (!ingredientType.isMixedIngredient() || !ingredientType.hasSpecialName())
     *          | then result.equals( getPreName() + ingredientType.getName() + getPostName())
     *          | else result.equals(getSpecialName() + " (" + getPreName() + getName() + getPostName() + ")")
     */
    public String getFullName() {
        String pre = getPreName();
        String post = getPostName();
        if (!ingredientType.isMixedIngredient() || !ingredientType.hasSpecialName()) {
            return pre + ingredientType.getName() + post;
        } else {
            return getSpecialName() + " (" + pre + getName() + post + ")";
        }
    }

    /**
     * Returns the pre name.
     *
     * @return  If the alchemical ingredient is cooled then "Cooled" is returned, otherwise is the alchemical ingredient
     *          is heated then "Heated" is returned
     *          | if (temperature < standardTemperature)
     *          | then result.equals("Cooled")
     *          | else result.equals("Heated")
     */
    private String getPreName() { // to simplify expansion
        // TODO: could be saved as a field and changed when the respective property has changed [OPTIONAL]
        // If we decide not to do this, change to regular comment and remove the optional tag ^
        String pre = "";
        long temperature = asLong(this.getTemperature());
        long standardTemperature = asLong(this.getStandardTemperature());
        if (temperature < standardTemperature) {
            pre += "Cooled ";
        } else if (temperature > standardTemperature) {
            pre += "Heated ";
        }
        return pre;
    }

    /**
     * Returns the post name. Right now this is always an empty string.
     */
    private String getPostName() { // to simplify expansion
        String post = "";
        return post;
    }

    /**
     * Returns the temperature as a long integer.
     * @param   temperature
     *          The temperature as an array.
     */
    private static long asLong(long[] temperature) {
        if (temperature[0] != 0) {
            return -temperature[0];
        } else {
            return temperature[1];
        }
    }

    /**
     * Returns the special name of a mixed alchemical ingredient.
     * @return  if the alchemical ingredient is mixed returns the special name.
     *          | if (ingredientType.isMixedIngredient())
     *          | result.equals(ingredientType.getSpecialName())
     */
    public String getSpecialName() {
        if (ingredientType.isMixedIngredient()) {
            return ingredientType.getSpecialName();
        } else {
            return null;
        }
    }

    /**
     * Checks whether this alchemical ingredient has a special name
     *
     * @return  True if and only if this alchemical ingredient has a special name
     *          | result == (ingredientType.hasSpecialName())
     */
    public boolean hasSpecialName(){
        return ingredientType.hasSpecialName();
    }

    /**
     * Set the special name of the (mixed) alchemical ingredient.
     * @param   specialName
     *          the special name of this (mixed) alchemical ingredient.
     * @throws NonMixedSpecialNameException(this.ingredientType)
     *          The alchemical ingredient is not a mixed ingredient
     *          | !ingredientType.isMixedIngredient()
     */
    public void setSpecialName(String specialName) throws NonMixedSpecialNameException, IllegalNameException {
        if (!ingredientType.isMixedIngredient()){
            throw new NonMixedSpecialNameException(ingredientType);
        }
        else {
            ingredientType.setSpecialName(specialName);
        }
    }

    /* ***************************
     * INGREDIENT TYPE - STANDARD TEMPERATURE
     * TOTAAL
     * ***************************/

    /**
     * Return the standard temperature of this alchemical ingredient
     */
    public long[] getStandardTemperature() {
        return ingredientType.getStandardTemperature();
    }

    /* ***************************
     * INGREDIENT TYPE - STANDARD STATE
     * TOTAAL (want IngredientType is totaal?)
     * ***************************/

    /**
     * Return the standard state of this alchemical ingredient.
     */
    public State getStandardState() {
        return ingredientType.getStandardState();
    }

    /* *********************************************************
     * STATE
     * *********************************************************/

    /**
     * Return the (current) state of this alchemical ingredient.
     */
    public State getState() {
        return state;
    }

    /**
     * Return whether the given state is a valid state for an alchemical ingredient.
     *
     * @param   state
     *          The state to check.
     * @return  True if and only if the given state is effective
     *          | result == (state != null)
     */
    public static boolean isValidState(State state){
        return state != null;
    }

    /* *********************************************************
     * AMOUNT & UNIT
     * NOMINAAL
     * *********************************************************/

    /**
     * Return the amount of this alchemical ingredient.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Return whether the given amount is a valid amount for an alchemical ingredient.
     *
     * @param   amount
     *          The amount to check.
     * @return  True if and only if the given amount is greater or equal to zero
     *          | result == (amount >= 0)
     */
    public static boolean isValidAmount(int amount){
        return amount >= 0;
    }

    /**
     * Return the unit of this alchemical ingredient.
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Return whether the given unit is a valid unit for an alchemical ingredient.
     *
     * @param   unit
     *          The unit to check.
     * @return  True if and only if the given unit is effective
     *          | result == (unit != null)
     */
    public static boolean isValidUnit(Unit unit){
        return unit != null;
    }

    /* *********************************************************
     * TEMPERATURE
     * TOTAAL
     * *********************************************************/

    /**
     * Return the (current) temperature of this alchemical ingredient.
     */
    public long[] getTemperature() {
        return asLongArray(this.temperature);
    }

    /**
     * Returns the long array version of the given temperature.
     * @param   temperature
     *          the long temperature that needs to be given as a long array
     */
    private long[] asLongArray(long temperature){
        long[] temp = new long[2];
        if (temperature < 0) {
            temp[0] = Math.abs(temperature);
        } else {
            temp[1] = temperature;
        }
        return temp;
    }

    /**
     * Set the (current) temperature of this alchemical ingredient.
     *
     * @param   temperature
     *          The new (current) temperature
     *
     * @post    If the new temperature is valid, the new temperature is set as the temperature of this alchemical
     *          ingredient, otherwise the temperature of this alchemical ingredient is set to the long representation
     *          of {0,20}
     *          | if (!isValidTemperature(temperature))
     *          | new.getTemperature() == {0,20}
     *          | else new.getTemperature() == temperature
     */
    private void setTemperature(long[] temperature) { // input : array
        if (!isValidTemperature(temperature)) {
            this.temperature = 20; //DEFAULT
        }
        else {
            this.temperature = temperature[1] - temperature[0];
        }
    }

    /**
     * Checks whether te given temperature is a valid temperature
     * @param   temperature
     *          The temperature to check
     *
     * @return  True if and only if the temperature is not both cooled and heated and neither exceed the maximum
     *          temperature
     *          | result == (temperature != null
     *          |               && temperature.length == 2
     *          |               && (temperature[0] == 0 || temperature[1] == 0)
     *          |               && temperature[0] <= AlchemicIngredient.MAX_TEMPERATURE
     *          |               && temperature[1] <= AlchemicIngredient.MAX_TEMPERATURE)
     */
    public static boolean isValidTemperature(long[] temperature) {
        return temperature != null
                && temperature.length == 2
                && (temperature[0] == 0 || temperature[1] == 0)
                && temperature[0] <= AlchemicIngredient.MAX_TEMPERATURE
                && temperature[1] <= AlchemicIngredient.MAX_TEMPERATURE;
    }

    /**
     * Changes this temperature to a higher temperature.
     * @param   temperatureDifference
     *          the amount at which the temperature is raised
     */
    public void heat(long temperatureDifference) {
        if (this.temperature + temperatureDifference <= MAX_TEMPERATURE) {
            this.temperature += temperatureDifference;
        }
    }

    /**
     * Changes this temperature to a lower temperature.
     * @param   temperatureDifference
     *          the amount at which the temperature is lowered
     */
    public void cool(long temperatureDifference) {
        if (this.temperature - temperatureDifference >= -MAX_TEMPERATURE) {
            this.temperature -= temperatureDifference;
        }
    }


    /* *********************************************************
     * COPY METHODS:
     * When you have to make a new ingredient with practically
     * everything the same except for a few values
     * *********************************************************/

    public AlchemicIngredient copyAllValsExcept(int amount, Unit unit, State currentState, long[] currentTemperature) {
        AlchemicIngredient newIngredient = new AlchemicIngredient(amount, unit, getStandardTemperature(),
                currentTemperature, getName(), getStandardState(), currentState);
        if (hasSpecialName()){
            newIngredient.setSpecialName(this.getSpecialName());
        }
        return newIngredient;
    }

    /** TODO: dit is nog niet OK
     * Makes a new alchemical ingredient based on an existing alchemical ingredient but with a different amount, unit
     * and current state.
     * @param   amount
     *          the new amount for the new alchemical ingredient
     * @param   unit
     *          the new unit for the new alchemical ingredient
     * @param   currentState
     *          the new (current) state for the new alchemical ingredient
     * @return  a new AlchemicIngredient object where all fields are the same as this object, but with different
     *          amount, unit and state.
     *          | result == new AlchemicIngredient(
     *          |               amount,
     *          |               unit,
     *          |               getStandardTemperature(),
     *          |               getTemperature(),
     *          |               getName(),
     *          |               getStandardState(),
     *          |               currentState)
     */
    public AlchemicIngredient copyAllValsExcept(int amount, Unit unit, State currentState) {
        return copyAllValsExcept(amount, unit, currentState, getTemperature());
    }

    /**
     * Makes a new alchemical ingredient based on an existing alchemical ingredient but with a different amount and unit.
     * @param   amount
     *          the new amount for the new alchemical ingredient
     * @param   unit
     *          the new unit for the new alchemical ingredient
     * @return  AlchemicIngredient with the same state, but with a different amount and unit.
     */
    public AlchemicIngredient copyAllValsExcept(int amount, Unit unit) {
        return copyAllValsExcept(amount, unit, getState());
    }

    /* *********************************************************
     * MIX METHODES
     * *********************************************************/

    /** TODO IS DIT NODIG?
     * Return a new (mixed) alchemical ingredient based on this alchemical ingredient and a given alchemical ingredient
     *
     * @param   ingredient
     *          the alchemical ingredient which need to be mixed with this alchemical ingredient.
     */
    public AlchemicIngredient mixWith(AlchemicIngredient ingredient) {

        // Create a sorted list with the names of all the component ingredients
        List<String> names = mergeSort(this.getIngredientType().getComponentNames(),
                ingredient.getIngredientType().getComponentNames());

        // Create a new list with both ingredients
        List<AlchemicIngredient> ingredients = new ArrayList<>();
        ingredients.add(this);
        ingredients.add(ingredient);

        // Find the state of the ingredient with temperature closest to {0, 20}
        State state = this.standardTemperatureClosestToZeroTwenty(ingredients).getState();

        // Find the total amount in the largest fitting unit
        Quantity quantity = state.findLargestFit(state.addAmounts(ingredients), state.getSmallestUnit());
        int amount = quantity.getAmount();
        Unit unit = quantity.getUnit();

        // Calculate the average temperature
        long[] temperature = asLongArray((asLong(getTemperature()) +  asLong(ingredient.getTemperature()))/2);

        // Create a new mixedIngredientType
        MixedIngredientType mixedIngredientType = new MixedIngredientType(names, temperature, state);

        // Pass all the variable to make a new ingredient
        return new AlchemicIngredient(mixedIngredientType, amount, unit, temperature, state);
    }


    /**
     * Return a new (mixed) alchemical ingredient based on this alchemical ingredient and the given list of alchemical
     * ingredients
     *
     * @param   ingredients
     *          the list of alchemical ingredients which need to be mixed with this alchemical ingredient.
     */
    public AlchemicIngredient mixWith(List<AlchemicIngredient> ingredients){

        // Create a new list with the names of the component ingredients
        List<String> names = new ArrayList<>(this.getIngredientType().getComponentNames());

        // Initialize vars for finding average temperature
        long temperatureSum = asLong(getTemperature());
        int numberOfIngredients = 1;

        // Add all names to list, in order, and update temperature vars
        for (AlchemicIngredient alchemicIngredient: ingredients){
            names = mergeSort(names, alchemicIngredient.getIngredientType().getComponentNames());
            temperatureSum += asLong(alchemicIngredient.getTemperature());
            numberOfIngredients++;
        }

        // Find the average temperature based on values of the temperature vars
        long[] temperatureArray = asLongArray(temperatureSum/numberOfIngredients);

        // Find the state of the ingredient with its temperature closest to {0, 20}
        // Note: while a bit unclear, this does include *this* ingredient
        State state = this.standardTemperatureClosestToZeroTwenty(ingredients).getState();

        // Add this ingredient to the list of ingredients
        ingredients.add(this);

        // Calculate the new amount and unit
        Quantity quantity = state.findLargestFit(state.addAmounts(ingredients), state.getSmallestUnit());
        int amount = quantity.getAmount();
        Unit unit = quantity.getUnit();

        // Create a new mixedIngredientType
        MixedIngredientType mixedIngredientType = new MixedIngredientType(names, temperatureArray, state);

        // Pass all variables to create a new ingredient and return
        return new AlchemicIngredient(mixedIngredientType, amount, unit, temperatureArray, state);
    }

    /**
     * Checks if this alchemical ingredient temperature is closer to the temperature {0,20} than the temperature of the
     * given alchemical ingredient
     * @param   ingredient
     *          the given alchemical ingredient
     * @return  True if and only if this temperature is closer to {0,20} than the temperature of the given alchemical
     *          ingredient, otherwise false.
     *          | result == (
     *          |           (this.getTemperature()[0] + Math.abs(this.getTemperature()[1]-20)) <=
     *          |           (ingredient.getTemperature()[0] + Math.abs(ingredient.getTemperature()[1]-20))
     *          |           )
     */
    private boolean standardTemperatureCloserToZeroTwenty(AlchemicIngredient ingredient) {
        long[] tTemperature = getTemperature();
        long[] iTemperature = ingredient.getTemperature();
        long tDistance = tTemperature[0] + Math.abs(tTemperature[1]-20);
        long iDistance = iTemperature[0] + Math.abs(iTemperature[1]-20);
        return tDistance <= iDistance;
    }

    /**
     * Return the alchemical ingredient whose temperature is closest to the temperature {0,20}, the alchemical
     * ingredients to compare are this alchemical ingredient and the given ingredients
     * @param   ingredients
     *          the list of alchemical ingredients to compare
     */
    private AlchemicIngredient standardTemperatureClosestToZeroTwenty(List<AlchemicIngredient> ingredients){
        AlchemicIngredient closestIngredient = this;
        int i = 0;
        while (i < ingredients.size()) {
            AlchemicIngredient ingredientToCheck = ingredients.get(i);
            if (closestIngredient.standardTemperatureCloserToZeroTwenty(ingredientToCheck)){
                i +=1;
            }
            else {
                closestIngredient = ingredientToCheck;
                i +=1;
            }
        }
        return closestIngredient;
    }
    // TODO: getName, getSpecialName, setSpecialName, etc.


    /**
     * Returns a sorted merged list from two given sorted list.
     * @param   list1
     *          the first sorted list
     * @param   list2
     *          the second sorted list
     */
    private List<String> mergeSort(List<String> list1, List<String> list2){
        List<String> mergedList = new LinkedList<>();
        int n1 = list1.size();
        int n2 = list2.size();
        list1.add("");
        int i = 0;
        int j = 0;
        while (i<n1 && j<n2){
            if (list1.get(i).compareTo(list2.get(j))<=0){ //list1.get(i) is lexicographically less than list2.get(j)
                mergedList.add(list1.get(i));
                i +=1;
            }
            else {
                mergedList.add(list2.get(j));
                j += 1;
            }
        }
        while (i<n1){
            mergedList.add(list1.get(i));
            i +=1;
        }
        while (j<n2){
            mergedList.add(list2.get(j));
            j +=1;
        }
        return mergedList;
    }

    public AlchemicIngredient inStandardValues() {
        Quantity quantity = getState().convertTo(getAmount(), getUnit(), getStandardState());
        return this.copyAllValsExcept(quantity.getAmount(), quantity.getUnit(),
                getStandardState(), getStandardTemperature());
    }
}