package main.java.ingredient;

import be.kuleuven.cs.som.annotate.Raw;

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
     * Variable registering the quantity of this alchemical ingredient
     */
    private final int quantity;
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
    /** todo controleren pls
     * Initialize a new alchemical ingredient with given quantity, unit, standard temperature, current temperature, name,
     * standard type and current type.
     *
     * @param   quantity
     *          The quantity of the new alchemical ingredient
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
     * @post    The unit of this new alchemical ingredient is set to the given unit
     *          | new.getUnit() == unit
     * @post    The state of this new alchemical ingredient is set to the given currentState
     *          | new.getState() == currentState
     */
    @Raw
    public AlchemicIngredient(int quantity, Unit unit, long[] standardTemperature, long[] currentTemperature, String name,
                              State standardState, State currentState) {
        this.ingredientType = new IngredientType(name, standardTemperature, standardState);
        this.quantity = quantity;

        // Only if the given unit is representative of a quantity in the given state
        // can we allow an ingredient to be made
        if (!currentState.hasUnit(unit))
            throw new IllegalArgumentException();
        this.unit = unit;
        setTemperature(currentTemperature);
        this.state = currentState;
    }

    // FOR IN TRANSMOGIFIER: CURRENT STATE

    /**
     * Initialize a new alchemical ingredient with given quantity, unit, standard temperature, name, standard type and
     * current type.
     *
     * @param   quantity
     *          The quantity of the new alchemical ingredient
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
     * @effect  This new alchemical ingredient is initialized with the given quantity, unit, standard temperature,
     *          name, standard type and current type.
     *          the new alchemical ingerdient has the standard temperature as temperature
     *          | this(quantity, unit, standardTemperature, standardTemperature, name, standardState, currentState)
     */
    @Raw
    public AlchemicIngredient(int quantity, Unit unit, long[] standardTemperature,
                              String name, State standardState, State currentState) {
        this(quantity, unit, standardTemperature, standardTemperature, name, standardState, currentState);
    }

    // BASIC LONG CONSTRUCTOR

    /**
     * Initialize a new alchemical ingredient with given quantity, unit, standard temperature, name and standard type.
     *
     * @param   quantity
     *          The quantity of the new alchemical ingredient
     * @param   unit
     *          The unit of the new alchemical ingredient
     * @param   standardTemperature
     *          The standard temperature of the new alchemical ingredient
     * @param   name
     *          The name of the new alchemical ingredient
     * @param   standardState
     *          The standard state of the new alchemical ingredient
     *
     * @effect  This new alchemical ingredient is initialized with the given quantity, unit, standard temperature,
     *          name and standard type.
     *          the new alchemical ingerdient has the standard temperature as temperature and standard state as state
     *          | this(quantity, unit, standardTemperature, standardTemperature, name, standardState, standardState)
     */
    @Raw
    public AlchemicIngredient(int quantity, Unit unit, long[] standardTemperature, String name, State standardState) {
        this(quantity, unit, standardTemperature, standardTemperature, name, standardState, standardState);
    }

    // LONG CONSTRUCTOR WITHOUT UNIT - DEFAULT UNIT = SPOON

    /**
     * Initialize a new alchemical ingredient with given quantity, standard temperature, name and standard type.
     * @param   quantity
     *          The quantity of the new alchemical ingredient
     * @param   standardTemperature
     *          The standard temperature of the new alchemical ingredient
     * @param   name
     *          The name of the new alchemical ingredient
     * @param   standardState
     *          The standard state of the new alchemical ingredient
     *
     * @effect  This new alchemical ingredient is initialized with the given quantity, standard temperature, name and
     *          standard type.
     *          the new alchemical ingerdient has the standard temperature as temperature, standard state as state and
     *          Unit.Spoon as unit.
     *          | this(quantity, Unit.Spoon, standardTemperature, name, standardState)
     */
    @Raw
    public AlchemicIngredient(int quantity, long[] standardTemperature, String name, State standardState) {
        this(quantity, Unit.Spoon, standardTemperature, name, standardState);
    }

    // BASIC SHORT CONSTRUCTOR

    /**
     * Initialize a new alchemical ingredient with given quantity and unit.
     * @param   quantity
     *          The quantity of the new alchemical ingredient
     * @param   unit
     *          The unit of the new alchemical ingredient
     *
     * @effect  This new alchemical ingredient is initialized with the given quantity and unit.
     *          the alchemic ingredient has the name "Water" and has a standard temperature (and temperature) of {0,20}
     *          and the standard state (and state) is State.Liquid.
     *          | this(quantity, unit, new long[]{0, 20}, "Water", State.Liquid)
     */
    @Raw
    public AlchemicIngredient(int quantity, Unit unit) {
        this(quantity, unit, new long[]{0, 20}, "Water", State.Liquid);
    }

    // SHORT CONSTRUCTOR WITHOUT UNIT - DEFAULT UNIT = SPOON

    /**
     * Initialize a new alchemical ingredient with given quantity.
     * @param   quantity
     *          The quantity of the new alchemical ingredient
     *
     *
     * @effect  This new alchemical ingredient is initialized with the given quantity.
     *          the alchemic ingredient has the name "Water" and has a standard temperature (and temperature) of {0,20},
     *          the standard state (and state) is State.Liquid and Unit.Spoon as unit
     *          | this(quantity, unit, new long[]{0, 20}, "Water", State.Liquid)
     */
    @Raw
    public AlchemicIngredient(int quantity) {
        this(quantity, Unit.Spoon);
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
        } else if (temperature > standardTemperature) {  // Who's the silly goose who forgot that temperatures could be equal? INE =) ma kheb er nu aan gedacht en kgingt aanpassen =)
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
     * Returns the special name of a mixed alchemic ingredient.
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

    /* ***************************
     * INGREDIENT TYPE - STANDARD TEMPERATURE
     * TOTAAL
     * ***************************/

    /**
     *
     * @return
     */
    public long[] getStandardTemperature() {
        return ingredientType.getStandardTemperature();
    }

    /* ***************************
     * INGREDIENT TYPE - STANDARD STATE
     * TOTAAL (want IngredientType is totaal?)
     * ***************************/

    /**
     *
     * @return
     */
    public State getStandardState() {
        return ingredientType.getStandardState();
    }

    /* *********************************************************
     * STATE
     * ?
     * *********************************************************/

    /**
     *
     * @return
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
     * QUANTITY & UNIT
     * NOMINAAL
     * *********************************************************/

    /**
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return
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
     *
     * @return
     */
    public long[] getTemperature() {
        long[] temp = new long[2];
        if (this.temperature < 0) {
            temp[0] = Math.abs(this.temperature);
        } else {
            temp[1] = this.temperature;
        }
        return temp;
    }


    // MOET TOTAAL ZIJN, dus geen exceptions

    /**
     *
     * @param temperature
     */
    private void setTemperature(long[] temperature) { // input : array
        if (!isValidTemperature(temperature)) {
            this.temperature = 20; //DEFAULT
        }
        else {
            long temp = 0;
            if (temperature[0] != 0) {
                temp -= temperature[0];
            } else {
                temp = temperature[1];
            }
            this.temperature = temp;
        }
    }

    /**
     *
     * @param temperature
     * @return
     */
    private static boolean isValidTemperature(long[] temperature) {
        if (temperature[0] != 0 && temperature.length == 2) {
            return temperature[1] == 0 && temperature[0] <= MAX_TEMPERATURE;
        } else
            return temperature.length == 2 && temperature[1] <= MAX_TEMPERATURE; // dus temperature[0] == 0 -> array mag nog steeds maar 2 lang zijn en
    }

    /**
     *
     * @param temperature
     */
    public void heat(long temperature) {
        if (this.temperature + temperature <= MAX_TEMPERATURE) {
            this.temperature += temperature;
        }
    }

    /**
     *
     * @param temperature
     */
    public void cool(long temperature) {
        if (this.temperature - temperature >= -MAX_TEMPERATURE) {
            this.temperature -= temperature;
        }
    }

    // TODO: getName, getSpecialName, setSpecialName, etc.
}