import java.util.EnumSet;

public enum Quantity {
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
    Pinch((float)(1.0/6.0), false, State.Powder, "Pinch", "Pinches"),

    Sachet(7, true, State.Powder, "Sachet", "Sachets"),
    Box(42, true, State.Powder, "Box", "Boxes"),
    Sack(126, true, State.Powder, "Sack", "Sacks"),
    Chest(1260, true, State.Powder, "Chest", "Chests"),
    ;

    // Properties
    private final float value;
    private final boolean canBeContainer;
    private final State respectiveState;
    private final String nameSingular;
    private final String namePlural;

    Quantity(float value, boolean canBeContainer, State respectiveState, String nameSingular, String namePlural) {
        this.value = value;
        this.canBeContainer = canBeContainer;
        this.respectiveState = respectiveState;
        this.nameSingular = nameSingular;
        this.namePlural = namePlural;
    }

    // Getters
    public float getValue() {
        return value;
    }

    public boolean isCanBeContainer() {
        return canBeContainer;
    }

    public State getRespectiveState() {
        return respectiveState;
    }

    public String getNameSingular() {
        return nameSingular;
    }

    public String getNamePlural() {
        return namePlural;
    }

    /**
     * Returns the amount in the resulting "to" Quantity, given the amount in the given "from" Quantity. This amount,
     * if it is a fraction, will be rounded down.
     *
     * @param from      The Quantity from which we want to convert
     * @param to        The Quantity to which we want to convert
     * @param amount    The amount of product, represented in the "from" Quantity.
     * @return          The amount of product, represented in the "to" Quantity
     */
    public int convertBetweenQuantities(Quantity from, Quantity to, int amount) {
        // Will automatically round down:
        return (int) (amount * from.getValue() / to.getValue());
    }

    public Quantity findLargestFit(State state, int amount) {
        for(Quantity quantity: EnumSet.allOf(Quantity.class)) {
            // W.I.P.
            // Should amount be int or float? Probably float?
        }
        return null;
    }
}