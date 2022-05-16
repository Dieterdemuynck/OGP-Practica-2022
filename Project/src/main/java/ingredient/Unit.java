package main.java.ingredient;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.EnumSet;
import java.util.Scanner;

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
        // Will automatically round down:
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
        return convertBetweenQuantities(this, targetUnit, amount);
    }

    /**
     * Finds the largest fit given a certain State and an amount in Spoons.
     *
     * @param state  The State we want the Unit to represent.
     * @param amount The amount of ingredient we try to find a fitting Unit for.
     * @return       The largest Unit which fits the given amount.
     */
    static public Unit findLargestFit(State state, double amount) {
        double largestFoundValue = -1;
        Unit largestUnit = null;

        /* Checks *every* quantity, including quantities of other states. Possibly, horribly, inefficient.
         * T(n) = O(n) with n = amount of defined quantities.
         * We could save a list or hashmap somewhere which links a state to an (ordered) list/array of Quantities,
         * which could increase performance, if necessary.
         */
        for (Unit unit : EnumSet.allOf(Unit.class)) {
            if (unit.getRespectiveState() == state || unit.getRespectiveState() == null) {
                /* Messy workaround to fix rounding errors: Calculate modulus using doubles, convert to float,
                 * then compare float quantities to float. After rounding, we have either 0 or the previous amount.
                 * My foal is to write code so terribly awful I never have to work on conversion methods again.
                 * I /could/ clean this up using BigDecimals, which I learnt about *after* writing this. Not happening.
                 */
                if (unit.getValue() > largestFoundValue && ((float)(amount % unit.getValue()) == (float)0
                    || (float)(amount % unit.getValue()) == (float)(unit.getValue()))) {
                    largestFoundValue = unit.getValue();
                    largestUnit = unit;
                }
            }
        }

        return largestUnit;
    }

    /**
     * Finds the smallest container Unit which can hold a given amount in Spoons in the respective State.
     *
     * @param state  The State we want the Unit to represent.
     * @param amount The amount of ingredient we try to find a container Unit for.
     * @return       The smallest container Unit that can hold the given amount.
     */
    static public Unit findSmallestFittingContainer(State state, double amount) {
        double smallestFoundValue = -1;
        Unit smallestContainer = null;

        // Same issue in terms of time complexity as with findLargestFit()
        for (Unit unit : EnumSet.allOf(Unit.class)) {
            if (unit.getRespectiveState() == state || unit.getRespectiveState() == null) {
                if (unit.canBeContainer() && unit.getValue() >= amount
                        && unit.getValue() < smallestFoundValue) {
                    // Container Unit found which is smaller and still fits the given amount.
                    smallestFoundValue = unit.getValue();
                    smallestContainer = unit;
                }
            }
        }

        return smallestContainer;
    }

    private static Scanner getTableScanner() throws FileNotFoundException {
        return new Scanner(new File(String.valueOf(Unit.class.getResource("alchemyUnits.cnt"))));
    }


}
