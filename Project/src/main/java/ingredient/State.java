package main.java.ingredient;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.Arrays;
import java.util.List;

/**
 * State enum, which holds the state an object is in. Each state object also holds a table for conversions between
 * Units in that respective state.
 *
 */
public enum State {
    // -=-=-=-=-=-=-=-=-=-=-=- POWDER -=-=-=-=-=-=-=-=-=-=-=- \\
    Powder(
            new Unit[]{
                    Unit.Pinch,
                    Unit.Spoon,
                    Unit.Sachet,
                    Unit.Box,
                    Unit.Sack,
                    Unit.Chest,
                    Unit.Storeroom
            },
            new int[] {1, 6, 7, 6, 3, 10, 5}
    ),

    // -=-=-=-=-=-=-=-=-=-=-=- LIQUID -=-=-=-=-=-=-=-=-=-=-=- \\
    Liquid(
            new Unit[]{
                    Unit.Drop,
                    Unit.Spoon,
                    Unit.Vial,
                    Unit.Bottle,
                    Unit.Jug,
                    Unit.Barrel,
                    Unit.Storeroom
            },
            new int[] {1, 8, 5, 3, 7, 12, 5}
    );

    /* *********************************************************
     * PROPERTIES
     * *********************************************************/

    private final Unit[] units;
    private final int[] values;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Creates a new State object with the respective Units and the values of the given units.
     *
     * @pre     unit array and value array must have the same length
     *          | units.length == values.length
     * @pre     unit array is not null
     *          | units != null
     * @pre     value array is not null
     *          | values != null
     * @pre     There is a Spoon unit in the units array
     *          | newUnit.Spoon != null
     * @pre     There is a Storeroom unit in the units array
     *          | newUnit.Storeroom != null
     * @pre     The value of a Storeroom must be 6300 times the value of a Spoon
     *          | for (i =2, i == values.size(), i++) {total *= values[i];}
     *          | total == 6300
     * @pre     the first value must be 1
     *          | values[0] == 1
     * @pre     the value for Unit.Storeroom must eventually result in 6300 times the value for Unit.Spoon
     *          | TODO: formal spec. (checker?)
     * @post    A new State with Units (of which there is at least a Spoon and a Storeroom) and it's values is created.
     *
     * @param   units   The units that belong to the State.
     * @param   values  The value of the unit at the same index in the units array compared to the one at the previous.
     */
    @Raw
    State(Unit[] units, int[] values) {

        // The two arrays must have the same length
        assert units.length == values.length;

        // We update the values array such that each Unit has its value compared to the smallest Unit.
        // First value must be 1, second value would remain equal, only the third value will change.
        for (int i = 2; i < units.length; i++) {
            values[i] *= values[i-1];
        }

        this.units = units;
        this.values = values;
    }

    /* *********************************************************
     * GETTERS
     * *********************************************************/

    @Immutable
    @Basic
    private Unit[] getUnits() {
        return units;
    }

    @Immutable
    @Basic
    private int[] getValues() {
        return values;
    }

    /* *********************************************************
     * QoL METHODS
     * LEVEL 1: PRIVATE
     * *********************************************************/

    private int getIndexOf(Unit unit) {
        return Arrays.asList(this.getUnits()).indexOf(unit);
    }

    private Unit getUnitAt(int i) {
        return this.getUnits()[i];
    }

    @Immutable
    private int unitCount() {  // TODO: no arguments, and returns a value that doesn't change. @Immutable?
        return this.getUnits().length;
    }

    private int getValueOf(Unit unit) {
        return this.getValues()[this.getIndexOf(unit)];
    }

    private int getValueAt(int i) {
        return this.getValues()[i];
    }

    private boolean contains(Unit unit) {
        return Arrays.asList(this.getUnits()).contains(unit);
    }

    private int fromSpoons(double amountInSpoons) {
        return (int) (amountInSpoons * getValueOf(Unit.Spoon));
    }

    /* *********************************************************
     * QoL METHODS
     * LEVEL 2: PUBLIC
     * *********************************************************/

    @Immutable
    public Unit getSmallestUnit() {
        return getUnitAt(0);
    }

    public double inSpoons(int amount, Unit unit) {
        return (double) amount * getValueOf(unit) / (double) getValueOf(Unit.Spoon);
    }

    public double inStorerooms(int amount, Unit unit) {
        return (double) (amount * getValueOf(unit)) / (double) getValueOf(Unit.Storeroom);
    }

    /* *********************************************************
     * QoL METHODS
     * LEVEL 3: ALCHEMIC INGREDIENT (STATIC)
     * *********************************************************/

    public static double inSpoons(AlchemicIngredient ingredient) {
        return ingredient.getState().inSpoons(ingredient.getAmount(), ingredient.getUnit());
    }

    public static double inStorerooms(AlchemicIngredient ingredient) {
        return ingredient.getState().inStorerooms(ingredient.getAmount(), ingredient.getUnit());
    }

    /**
     * Returns the amount of AlchemicIngredient currently stored in the storedIngredients List in Storerooms.
     *
     * @pre     storedIngredients is not null
     *          | storedIngredients != null
     *
     * @param storedIngredients The List of ingredients we try to find the amount of
     * @return  The amount of ingredient stored in the List, in Storerooms
     *          | result == sum(for (AlchemicIngredient ingredient: storedIngredients) inStorerooms(ingredient))
     */
    public static double addAmountsInStorerooms(List<AlchemicIngredient> storedIngredients) {
        double amount = 0;
        for (AlchemicIngredient ingredient: storedIngredients) {
            amount += State.inStorerooms(ingredient);
        }
        return amount;
    }

    /**
     * Returns the amount of AlchemicIngredient currently stored in the storedIngredients List in Spoons.
     *
     * @pre     storedIngredients is not null
     *          | storedIngredients != null
     *
     * @param storedIngredients The List of ingredients we try to find the amount of
     * @return  The amount of ingredient stored in the List, in Spoons
     *          | result == sum(
     *          |     for (AlchemicIngredient ingredient: storedIngredients) inSpoons(ingredient)
     *          | )
     */
    public static double addAmountsInSpoons(List<AlchemicIngredient> storedIngredients) {
        double amount = 0;
        for (AlchemicIngredient ingredient: storedIngredients) {
            amount += State.inSpoons(ingredient);
        }
        return amount;
    }

    /* *********************************************************
     * SUBTRACTION
     * *********************************************************/

    public Quantity subtract(int amount1, Unit unit1, int amount2, Unit unit2) {
        return findLargestFit(
                (amount1 * getValueOf(unit1) - amount2 * getValueOf(unit2)),
                getSmallestUnit()
        );
    }

    /* *********************************************************
     * CHECKERS
     * *********************************************************/

    @Immutable
    public boolean hasContainer() {
        // A bit of a nasty way to check for a container, but it works!
        return findSmallestFittingContainer(0, getSmallestUnit()) != null;
    }

    public boolean hasUnit(Unit unit) {
        return this.contains(unit);
    }

    /**
     * Returns 1 if the first amount in its Unit is greater than the second amount in its Unit,
     * 0 if both amounts are equal, and -1 if the first amount in its Unit is less than the second in its Unit.
     * This can also be seen as being the sign of the difference between the two amount.
     *
     * @pre     unit1 is not null
     *          | unit1 != null
     * @pre     unit2 is not null
     *          | unit2 != null
     * @pre     unit1 is representative for this state
     *          | this.contains(unit1)
     * @pre     unit2 is representative for this state
     *          | this.contains(unit2)
     *
     * @param amount1   The first amount
     * @param unit1     The Unit of the first amount
     * @param amount2   The second amount
     * @param unit2     The Unit of the second amount
     * @return 1 if the first amount in its Unit is greater than the second amount in its Unit,
     *         0 if both amounts are equal
     *         -1 if the first amount in its Unit is less than the second amount in its Unit.
     *         | result == sign(amount1 * getValueOf(unit1) - amount2 * getValueOf(unit2))
     */
    public int compareInSameState(int amount1, Unit unit1, int amount2, Unit unit2) {
        return (int) Math.signum(amount1 * getValueOf(unit1) - amount2 * getValueOf(unit2));
    }

    /* *********************************************************
     * CONVERSION METHODS
     * *********************************************************/

    public Quantity convertTo(int amount, Unit originUnit, State targetState) {
        return targetState.findLargestFit(targetState.fromSpoons(this.inSpoons(amount, originUnit)), Unit.Spoon);
    }

    public static Quantity convertTo(AlchemicIngredient ingredient, State state) {
        return ingredient.getState().convertTo(
                ingredient.getAmount(), ingredient.getUnit(), state
        );
    }

    // TODO: if this is needed, uncomment. (I doubt it tho... delete it ig)
//    /**
//     * Returns the equivalent amount in the target Unit, given the amount in the origin Unit. This amount,
//     * if it is a fraction, will be rounded down.
//     *
//     * @param amount        The amount of product, represented in the origin Unit.
//     * @param originUnit    The Unit from which we want to convert
//     * @param originState   The State of the origin Unit.
//     * @param targetUnit    The Unit to which we want to convert
//     * @param targetState   The State of the target Unit.
//     * @return              The amount of product, represented in the target Unit
//     */
//    private static int convertBetweenDifferentStates(int amount, Unit originUnit, State originState,
//                                           Unit targetUnit, State targetState) {
//
//        // Step 1: Update amount to its value in smallest
//        amount *= originState.getValueOf(originUnit);
//
//        // Step 2: Move through origin table, from smallest to Spoon
//        // This may result in a fraction
//        double spoonAmount = (double) amount / originState.getValueOf(Unit.Spoon);
//
//        // Step 3: Move through the target table, from Spoon to smallest (and round)
//        amount = (int) (spoonAmount * targetState.getValueOf(Unit.Spoon));
//
//        // Step 4: Move through target table, from smallest to targetUnit
//        for (int i = 0; targetState.getUnitAt(i) != targetUnit; i++) {
//            amount /= targetState.getValueAt(++i);
//        }
//        return 0;
//    }

    /**
     * // TODO: check specification
     * Finds the largest unit which will still hold the given amount as an integer, given its current unit and the
     * required state. Will return a Quantity object holding both the resulting amount and Unit.
     *
     * @pre     amount is not 0
     *          | amount != 0
     * @pre     previousUnit is not null
     *          | previousUnit != null
     * @pre     state is not null
     *          | state != null
     * @pre     The given unit is representative of the given state
     *          | Arrays.asList(stateUnits).contains(previousUnit)
     *
     * @param   amount        The amount of ingredient we try to find a fitting Unit for.
     * @param   previousUnit  The original unit the amount is in.
     * @return  The largest Unit which fits the given amount, along with the respective amount in that Unit.
     *          | result == TODO: formal specification (how even?)
     */
    public Quantity findLargestFit(int amount, Unit previousUnit) {  // TODO: if not used outside class: private

        // We assume previousUnit is in fact in the array:
        assert this.contains(previousUnit);

        int i = this.getIndexOf(previousUnit);
        amount *= getValueAt(i);  // amount is now in smallest
        while (i < this.unitCount() && amount % this.getValueAt(i) == 0) {
            i++;
        }

        return new Quantity(amount / this.getValueAt(i-1), this.getUnitAt(i-1));
    }

    /**
     * // TODO: Check specification
     * Finds the smallest container Unit which can hold a given amount of a given Unit in this State. If none is
     * found, returns the largest container of this State. If this State has no container units, returns null.
     *
     * // TODO: pre conditions etc.
     *
     * @param   amount        The amount of ingredient we try to find a fitting container for.
     * @param   containedUnit The unit of the amount that will be contained.
     * @return       The smallest container Unit that can hold the given amount, or the largest container unit if none
     *               fit.
     */
    public Unit findSmallestFittingContainer(int amount, Unit containedUnit) {

        // We assume containedUnit is in fact in the array:
        assert this.contains(containedUnit);

        // Loop through units until containedUnit is found, while keeping track of the largest container Unit found
        int i = 0;
        int containerUnitIndex = -1;
        for (/* No statement */;getUnitAt(i) != containedUnit; i++) {
            if (getUnitAt(i).canBeContainer())
                containerUnitIndex = i;
        }

        // containedUnit has been found, check if it is a container
        if(getUnitAt(i).canBeContainer()) {
            containerUnitIndex = i;
        }

        amount *= getValueAt(i);  // We set amount to amount in smallest

        // As long as the amount is too large for the current containerUnit, and there is a next value,
        // check if the next unit can be a container
        while (amount > getValueAt(containerUnitIndex) && ++i < this.unitCount()) {
            // As long as the amount is larger than the current value AND there is a next value:
            if (getUnitAt(i).canBeContainer()) {
                // Check if the next value can be a container, if yes, set the current largest container to that one
                containerUnitIndex = i;
            }
        }

        // We now either have the largest possible container, or a fitting container.
        if (containerUnitIndex == -1)
            // There is no container unit for this State
            return null;
        return getUnitAt(containerUnitIndex);
    }
}
