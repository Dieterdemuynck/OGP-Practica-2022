package main.java.ingredient;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

// TODO: Update this horrible mess to use the unit table (see cnt file) instead.
public enum Unit {
    // State-independent
    Spoon(1, true, null, "Spoon", "Spoons"),
    Storeroom(6300, false, null, "Storeroom", "Storerooms"),

    // Liquid
    Drop((float)(1.0/8.0), false, State.Liquid, "Drop", "Drops"),

    Vial(5, true, State.Liquid, "Vial", "Vials"),
    Bottle(15, true, State.Liquid, "Bottle", "Bottles"),
    Jug(105, true, State.Liquid, "Jug", "Jugs"),
    Barrel(1260, true, State.Liquid, "Barrel", "Barrels"),

    // Powder
    Pinch(1.0/6.0, false, State.Powder, "Pinch", "Pinches"),

    Sachet(7, true, State.Powder, "Sachet", "Sachets"),
    Box(42, true, State.Powder, "Box", "Boxes"),
    Sack(126, true, State.Powder, "Sack", "Sacks"),
    Chest(1260, true, State.Powder, "Chest", "Chests"),
    ;

    // TODO: Delete all of the fields and getters etc.
    // Properties
    private final double value;
    private final boolean canBeContainer;
    private final State respectiveState;
    private final String nameSingular;
    private final String namePlural;

    Unit(double value, boolean canBeContainer, State respectiveState, String nameSingular, String namePlural) {
        this.value = value;
        this.canBeContainer = canBeContainer;
        this.respectiveState = respectiveState;
        this.nameSingular = nameSingular;
        this.namePlural = namePlural;
    }

    /* *********************************************************
     * VALUE
     * *********************************************************/
    public double getValue() {
        return value;
    }

    /* *********************************************************
     * CONTAINER
     * *********************************************************/
    public boolean canBeContainer() {
        return canBeContainer;
    }

    /* *********************************************************
     * STATE
     * *********************************************************/
    public State getRespectiveState() {
        return respectiveState;
    }

    /* *********************************************************
     * NAME SINGULAR
     * *********************************************************/
    public String getNameSingular() {
        return nameSingular;
    }

    /* *********************************************************
     * NAME PLURAL
     * *********************************************************/
    public String getNamePlural() {
        return namePlural;
    }

    /**
     * Returns the equivalent amount in the target Unit, given the amount in the origin Unit. This amount,
     * if it is a fraction, will be rounded down.
     *
     * @param originUnit    The Unit from which we want to convert
     * @param targetUnit    The Unit to which we want to convert
     * @param amount        The amount of product, represented in the origin Unit.
     * @return              The amount of product, represented in the target Unit
     */
    static public int convertBetweenQuantities(Unit originUnit, Unit targetUnit, int amount) {
        // TODO
        return (int) (amount * originUnit.getValue() / targetUnit.getValue());
    }

    /**
     * Returns the equivalent amount in the target Unit, given the amount in this object's Unit. This amount,
     * if it is a fraction, will be rounded down.
     *
     * @param targetUnit    The Unit to which we want to convert
     * @param amount        The amount of product, represented in this object's Unit.
     * @return              The amount of product, represented in the target Unit
     */
    public int convertTo(Unit targetUnit, int amount) {
        // TODO
        return convertBetweenQuantities(this, targetUnit, amount);
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
    static public Quantity findLargestFit(int amount, Unit previousUnit, State state){

        // Get the arrays needed
        Object[][] table = CNTParser.getTable(state);
        Unit[] stateUnits = (Unit[]) table[0];
        Integer[] stateValues = (Integer[]) table[1];

        // We assume previousUnit is in fact in the array:
        assert Arrays.asList(stateUnits).contains(previousUnit);

        // Loop through units until previousUnit is found
        int i = 0;
        while (stateUnits[i] != previousUnit) {
            i++;
        }

        // Divide by next value until the amount does not fit
        for( /* No statement */ ; amount % stateValues[i] == 0 && i < stateUnits.length; i++) {
            amount /= stateValues[i];
        }

        return new Quantity(amount, stateUnits[i]);
    }

    /**
     * Finds the smallest container Unit which can hold a given amount in Spoons in the respective State. If none is
     * found, returns the largest container of the respective State.
     *
     * // TODO: pre conditions etc.
     *
     * @param   amount        The amount of ingredient we try to find a fitting container for.
     * @param   containedUnit The unit of the amount that will be contained.
     * @param   state         The state of the returned container unit.
     * @return       The smallest container Unit that can hold the given amount, or the largest container unit if none
     *               fit.
     */
    static public Unit findSmallestFittingContainer(int amount, Unit containedUnit, State state) {

        // Get the arrays needed
        Object[][] table = CNTParser.getTable(state);
        Unit[] stateUnits = (Unit[]) table[0];
        Integer[] stateValues = (Integer[]) table[1];
        Boolean[] stateContainers = (Boolean[]) table[2];

        // We assume containedUnit is in fact in the array:
        assert Arrays.asList(stateUnits).contains(containedUnit);

        // Loop through units until containedUnit is found, while keeping track of the largest container Unit found
        int i = 0;
        Unit containerUnit = null;
        while (stateUnits[i] != containedUnit) {
            if (stateContainers[i])
                containerUnit = stateUnits[i];
            i++;
        }

        // containedUnit has been found, check if it is a container
        if(stateContainers[i]) {
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
            if (stateContainers[i])
                containerUnit = stateUnits[i];
        }

        return containerUnit;
    }

}
