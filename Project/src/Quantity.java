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

    Quantity(double value, boolean canBeContainer, State respectiveState, String nameSingular, String namePlural) {
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
     * Finds the largest fit given a certain State and an amount in Spoons.
     *
     * @param state  The State we want the Quantity to represent.
     * @param amount The amount of ingredient we try to find a fitting Quantity for.
     * @return       The largest Quantity which fits the given amount.
     */
    static public Quantity findLargestFit(State state, double amount) {
        double largestFoundValue = -1;
        Quantity largestQuantity = null;

        /* Checks *every* quantity, including quantities of other states. Possibly, horribly, inefficient.
         * T(n) = O(n) with n = amount of defined quantities.
         * We could save a list or hashmap somewhere which links a state to an (ordered) list/array of Quantities,
         * which could increase performance, if necessary.
         */
        for (Quantity quantity: EnumSet.allOf(Quantity.class)) {
            if (quantity.getRespectiveState() == state || quantity.getRespectiveState() == null) {
                /* Messy workaround to fix rounding errors: Calculate modulus using doubles, convert to float,
                 * then compare float quantities to float. After rounding, we have either 0 or the previous amount.
                 * My foal is to write code so terribly awful I never have to work on conversion methods again.
                 * I /could/ clean this up using BigDecimals, which I learnt about *after* writing this. Not happening.
                 */
                if (quantity.getValue() > largestFoundValue && ((float)(amount % quantity.getValue()) == (float)0
                    || (float)(amount % quantity.getValue()) == (float)(quantity.getValue()))) {
                    largestFoundValue = quantity.getValue();
                    largestQuantity = quantity;
                }
            }
        }

        return largestQuantity;
    }

    /**
     * Finds the smallest container Quantity which can hold a given amount in Spoons in the respective State.
     *
     * @param state  The State we want the Quantity to represent.
     * @param amount The amount of ingredient we try to find a container Quantity for.
     * @return       The smallest container Quantity that can hold the given amount.
     */
    static public Quantity findSmallestFittingContainer(State state, double amount) {
        double smallestFoundValue = -1;
        Quantity smallestContainer = null;

        // Same issue in terms of time complexity as with findLargestFit()
        for (Quantity quantity: EnumSet.allOf(Quantity.class)) {
            if (quantity.getRespectiveState() == state || quantity.getRespectiveState() == null) {
                if (quantity.canBeContainer() && quantity.getValue() >= amount
                        && quantity.getValue() < smallestFoundValue) {
                    // Container Quantity found which is smaller and still fits the given amount.
                    smallestFoundValue = quantity.getValue();
                    smallestContainer = quantity;
                }
            }
        }

        return smallestContainer;
    }
}