package main.java.ingredient;

import java.util.Arrays;

public enum State {
    // -=-=-=-=-=-=-=-=-=-=-=- POWDER -=-=-=-=-=-=-=-=-=-=-=-
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

    // -=-=-=-=-=-=-=-=-=-=-=- LIQUID -=-=-=-=-=-=-=-=-=-=-=-
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

    private final Unit[] units;
    private final int[] values;

    /**
     * Creates a new State object with the respective Units and the values of the given units.
     *
     * @pre     unit array and value array must have the same length
     *          | units.length == values.length
     * @pre     unit array is not null
     *          | units != null
     * @pre     value array is not null
     *          | values != null
     * @param   units   The units that belong to the State.
     * @param   values  The value of the unit at the same index in the units array compared to the one at the previous.
     */
    State(Unit[] units, int[] values) {
        assert units.length == values.length;
        this.units = units;
        this.values = values;
    }

    public Unit[] getUnits() {
        return units;
    }

    public int[] getValues() {
        return values;
    }

    /* *********************************************************
     * METHODS
     * *********************************************************/

    // TODO: Encapsulate further, function for unknown target Unit, function for same state conversion (maybe?)

    /**
     * Returns the equivalent amount in the target Unit, given the amount in the origin Unit. This amount,
     * if it is a fraction, will be rounded down.
     *
     * @param amount        The amount of product, represented in the origin Unit.
     * @param originUnit    The Unit from which we want to convert
     * @param originState   The State of the origin Unit.
     * @param targetUnit    The Unit to which we want to convert
     * @param targetState   The State of the target Unit.
     * @return              The amount of product, represented in the target Unit
     */
    static public int convertBetweenStates(int amount, Unit originUnit, State originState,
                                           Unit targetUnit, State targetState) {

        // Step 1: Move through origin table, from origin to smallest
        boolean atDecreasingValues = false;
        for (int i = originState.getUnits().length - 1; i >= 0; i--) {
            if (originState.getUnits()[i] == originUnit) {
                atDecreasingValues = true;
            }
            if (atDecreasingValues) {
                amount *= originState.getValues()[i];
            }
        }

        // Step 2: Move through origin table, from smallest to Spoon
        // This may result in a fraction
        double spoonAmount = amount;
        int valueIndex = 0;
        while (originState.getUnits()[valueIndex++] != Unit.Spoon) {
            spoonAmount /= originState.getValues()[valueIndex];
        }

        // Step 3: Move through the target table, from Spoon to smallest
        for (int i = targetState.getUnits().length - 1; i >= 0; i--) {
            spoonAmount *= targetState.getValues()[i];
        }
        // 3.1: convert to int (dropping anything past the comma)
        amount = (int) spoonAmount;

        // Step 4: Move through target table, from smallest to targetUnit
        for (int i = 0; targetState.getUnits()[i] != targetUnit; i++) {
            amount /= targetState.getValues()[++i];
        }
        return 0;
    }

    /**
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
     * @param   state         The state the final Unit must be in
     * @return  The largest Unit which fits the given amount.
     *          | result == TODO: formal specification (how even?)
     */
    public Quantity findLargestFit(int amount, Unit previousUnit, State state) {

        // We assume previousUnit is in fact in the array:
        assert Arrays.asList(this.getUnits()).contains(previousUnit);

        // Loop through units until previousUnit is found
        int i = 0;
        while (this.getUnits()[i] != previousUnit) {
            i++;
        }

        // Divide by next value until the amount does not fit
        for( /* No statement */ ; amount % this.getValues()[i] == 0 && i < this.getUnits().length; i++) {
            amount /= this.getValues()[i];
        }

        return new Quantity(amount, this.getUnits()[i]);
    }

    /**
     * Finds the smallest container Unit which can hold a given amount of a given Unit in this State. If none is
     * found, returns the largest container of this State.
     *
     * // TODO: pre conditions etc.
     *
     * @param   amount        The amount of ingredient we try to find a fitting container for.
     * @param   containedUnit The unit of the amount that will be contained.
     * @return       The smallest container Unit that can hold the given amount, or the largest container unit if none
     *               fit.
     */
    public Unit findSmallestFittingContainer(int amount, Unit containedUnit) {

        // Get the arrays needed
        Unit[] stateUnits = this.getUnits();
        int[] stateValues = this.getValues();

        // We assume containedUnit is in fact in the array:
        assert Arrays.asList(stateUnits).contains(containedUnit);

        // Loop through units until containedUnit is found, while keeping track of the largest container Unit found
        int i = 0;
        Unit containerUnit = null;
        while (stateUnits[i] != containedUnit) {
            if (stateUnits[i].canBeContainer())
                containerUnit = stateUnits[i];
            i++;
        }

        // containedUnit has been found, check if it is a container
        if(stateUnits[i].canBeContainer()) {
            containerUnit = stateUnits[i];
            // Check if amount already fits in the container
            if (amount <= 1) {
                // The contained Unit is returned
                return containerUnit;
            }
        }

        /* If this code is reached, the contained Unit is not a container Unit, or the amount to store is larger than 1.
         * The goal is to keep looking for a bigger container Unit until the amount fits, or until the largest Unit has
         * been checked. If the latter is the case, this means the amount cannot fit into a container Unit, thus we
         * return the largest found so far.
         */
        int sizeInContainedUnit = 1;
        while (amount > sizeInContainedUnit && ++i < stateUnits.length) {
            sizeInContainedUnit *= stateValues[i];
            if (stateUnits[i].canBeContainer())
                containerUnit = stateUnits[i];
        }

        return containerUnit;
    }
}
