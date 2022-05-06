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

    public boolean canBeContainer() {
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
     * Returns the equivalent amount in the target Quantity, given the amount in the origin Quantity. This amount,
     * if it is a fraction, will be rounded down.
     *
     * @param originQuantity    The Quantity from which we want to convert
     * @param targetQuantity    The Quantity to which we want to convert
     * @param amount            The amount of product, represented in the origin Quantity.
     * @return                  The amount of product, represented in the target Quantity
     */
    static public int convertBetweenQuantities(Quantity originQuantity, Quantity targetQuantity, int amount) {
        // Will automatically round down:
        return (int) (amount * originQuantity.getValue() / targetQuantity.getValue());
    }

    /**
     * Returns the equivalent amount in the target Quantity, given the amount in this object's Quantity. This amount,
     * if it is a fraction, will be rounded down.
     *
     * @param targetQuantity    The Quantity to which we want to convert
     * @param amount            The amount of product, represented in this object's Quantity.
     * @return                  The amount of product, represented in the target Quantity
     */
    public int convertTo(Quantity targetQuantity, int amount) {
        return convertBetweenQuantities(this, targetQuantity, amount);
    }

    /**
     * TODO: due to using floats, fractions will have horrible rounding errors during the runtime of this code,
     * messing up the entire purpose of some of the lines here. Looking for a solution... This may require
     * rewriting some of the base Quantity class' code.
     *
     * @param state
     * @param amount
     * @return
     */
    static public Quantity findLargestFit(State state, int amount) {
        float largestFoundValue = -1;
        Quantity largestQuantity = null;

        /* Checks *every* quantity, including quantities of other states. Possibly, horribly, inefficient.
         * T(n) = O(n) with n = amount of defined quantities.
         * Any ideas to improve this are welcome. An ordered array per state perhaps?
         * How do we link an array to a state?
         */
        for (Quantity quantity: EnumSet.allOf(Quantity.class)) {
            if (quantity.getRespectiveState() == state || quantity.getRespectiveState() == null) {
                if (quantity.getValue() > largestFoundValue && amount % quantity.getValue() == 0) {  // % don't work
                    largestFoundValue = quantity.getValue();
                    largestQuantity = quantity;
                }
            }
        }

        return largestQuantity;
    }
}