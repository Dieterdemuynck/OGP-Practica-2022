package main.java.ingredient;

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

    public boolean canBeContainer() {
        return canBeContainer;
    }

}
