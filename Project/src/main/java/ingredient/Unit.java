package main.java.ingredient;

/**
 * A class of units.
 * @author Dieter Demuynck, Hannes Ingelaere and Ine Malfait
 * @version 1.0
 */

public enum Unit {
    // State-independent
    Spoon(true),
    Storeroom(false),

    // Liquid
    Drop(false),

    Vial(true),
    Bottle(true),
    Jug(true),
    Barrel(true),

    // Powder
    Pinch(false),

    Sachet(true),
    Box(true),
    Sack(true),
    Chest(true),
    ;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    Unit(boolean canBeContainer) {
        this.canBeContainer = canBeContainer;
    }

    /* *********************************************************
     * CONTAINER
     * *********************************************************/

    private final boolean canBeContainer;

    /**
     * Returns whether a unit can be a container.
     * @return boolean of whether the unit can be a container.
     */
    public boolean canBeContainer() {
        return canBeContainer;
    }

}
